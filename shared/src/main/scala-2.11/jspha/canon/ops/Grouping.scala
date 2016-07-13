package jspha.canon.ops

import scala.language.higherKinds

private[canon] trait Grouping[Doc[_]] {

  /**
    * Add one additional acceptable representation to a document: one which
    * has been flattened eliminating all "non-essential" white space.
    */
  def group[A](doc: Doc[A]): Doc[A] =
    choice(flatten(doc), doc)

  /**
    * `choice(x, y)` is the union of the sets of documents in `x` and the
    * sets of documents in `y`.
    *
    * _Invariant_: all of the documents in `x` must
    * flatten to the same XML structure as all of the documents in `y`.
    *
    * _Invariant_: the left document should be "longer" than the right one.
    *
    * This operator distributes `cat` and `nest`:
    *
    *     choice(x, y) <> z = choice(x <> z, y <> z)
    *     x <> choice(y, z) = choice(x <> y, x <> z)
    *
    *     nest(i)(choice(x, y)) = choice(nest(i)(x), nest(i)(y))
    */
  protected def choice[A](x: Doc[A], y: Doc[A]): Doc[A]

  /**
    * Strips a document of all its variations but the one which has been
    * flattened removing all non-essential whitespace:
    *
    *     flatten(x <> y) = flatten(x) <> flatten(y)
    *     flatten(nil) = nil
    *     flatten(text(s)) = text(s)     // all text whitespace is essential
    *     flatten(char(c)) = char(c)     // characters are always essential
    *     flatten(line(rep)) = text(rep) // lines flatten to their replacement
    *     flatten(nest(i)(d)) = flatten(d)
    */
  protected def flatten[A](doc: Doc[A]): Doc[A]
}
