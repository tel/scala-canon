package jspha.canon.impl

import java.io.Writer

import jspha.canon.Document
import jspha.canon.ops

import scala.{Char => SChar}

/**
  * The simple reference implementation is more-or-less the final
  * implementation from Wadler's "A Prettier Printer". It's a nice
  * implementation in a lazy language but completely falls apart in a strict
  * one. It also does not handle annotations at all.
  */
sealed trait SimpleRef
  extends ops.Basic[Document]
    with ops.Grouping[Document]
    with ops.Interpreter[Document, ({ type l[A] = Writer => Unit })#l]{

  type D[A] = Document[A]

  final def cat[A](x: Document[A], y: Document[A]): Document[A] =
    Cat(x, y)

  final protected def text(string: String): Document[Nothing] =
    Text(string)

  final def line(replacement: String): Document[Nothing] =
    Line(replacement)

  final protected def char(c: SChar): Document[Nothing] =
    Char(c)

  final def nest[A](amt: Int)(doc: Document[A]): Document[A] =
    Nest(amt, doc)

  final val nil: Document[Nothing] =
    DNil

  final protected def choice[A](x: Document[A], y: Document[A]):
  Document[A] =
    Union(x, y)

  // TODO: Make tail recursive?
  final protected def flatten[A](doc: Document[A]): Document[A] = doc
  match {
    case DNil => DNil
    case Cat(x, y) => Cat(flatten(x), flatten(y))
    case Char(c) => Char(c)
    case Line(r) => Text(r)
    case Nest(i, x) => flatten(x)
    case Text(s) => Text(s)
    case Union(x, y) => flatten(x)
  }

  protected case object DNil extends D[Nothing]
  protected case class Cat[A](x: D[A], y: D[A]) extends D[A]
  protected case class Char(c: SChar) extends D[Nothing]
  protected case class Line(r: String) extends D[Nothing]
  protected case class Nest[A](amt: Int, x: D[A]) extends D[A]
  protected case class Text(t: String) extends D[Nothing]
  protected case class Union[A](x: D[A], y: D[A]) extends D[A]

  sealed trait Normal[+A]
  protected case object NNil extends Normal[Nothing]
  protected case class NText[A](s: String, next: Normal[A]) extends Normal[A]
  protected case class NChar[A](c: SChar, next: Normal[A]) extends Normal[A]
  protected case class NLine[A](amt: Int, next: Normal[A]) extends Normal[A]

  type DocList[A] = Seq[(Int, Document[A])]

  private final def rep[A](z: DocList[A]): Document[A] =
    z.map {
      case (indent, x) => nest(indent)(x)
    }.foldLeft[Document[A]](nil)(cat)

  private final def better[A](w: Int, pos: Int, x: Normal[A], y: Normal[A]): Normal[A] =
    if (fits(w - pos, x)) x else y

  private final def fits[A](w: Int, x: Normal[A]): Boolean =
    x match {
      case NNil => true
      case NText(s, next) => fits(w - s.length, next)
      case NChar(c, next) => fits(w - 1, next)
      case NLine(amt, next) => true
    }

  private final def best_[A](w: Int, pos: Int, dl: DocList[A]): Normal[A] =
    dl match {
      case Nil => NNil
      case (i, DNil) :: z => best_(w, pos, z)
      case (i, Cat(x, y)) :: z => best_(w, pos, (i, x) :: (i, y) :: z)
      case (i, Char(c)) :: z => NChar(c, best_(w, pos + 1, z))
      case (i, Line(r)) :: z => NLine(i, best_(w, pos, z))
      case (i, Nest(amt, x)) :: z => best_(w, pos, (amt + i, x) :: z)
      case (i, Text(s)) :: z => NText(s, best_(w, pos + s.length, z))
      case (i, Union(x, y)) :: z =>
        better(
          w, pos,
          best_(w, pos, (i, x) :: z),
          best_(w, pos, (i, y) :: z)
        )
    }

  private final def best[A](w: Int, pos: Int, doc: Document[A]): Normal[A] =
    best_(w, pos, (0, doc) :: Nil)

  private final def layout[A](n: Normal[A]): Writer => Unit =
    (w: Writer) =>
      n match {
        case NNil => ()
        case NText(s, next) => w append s ; layout(next)(w)
        case NChar(c, next) => w append c ; layout(next)(w)
        case NLine(amt, next) =>
          w append "\n"
          Range(0, amt).foreach { _ => w append ' ' }
          layout(next)(w)
      }

  final def pretty[A](width: Int, doc: Document[A]): Writer => Unit =
    layout(best(width, 0, doc))

}
