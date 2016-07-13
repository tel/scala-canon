package jspha.canon.ops

import scala.language.higherKinds
import scala.{Char => SChar}

private[canon] trait Basic[Doc[_]] {

  /**
    * Catenate two documents directly: `layout(cat(x, y)) = layout(x) +
    * layout(y)`
    */
  def cat[A](x: Doc[A], y: Doc[A]): Doc[A]

  /**
    * The empty document: `layout(nil) = ""`.
    */
  val nil: Doc[Nothing]

  /**
    * Generate a document from a string. All space is considered essential.
    * If you don't want this behavior see `string`.
    *
    * This forms a monoid homomorphism between strings and documents: `text
    * (s1) <> text(s2) = text(s1 ++ s2)` and `text("") = nil`.
    */
  protected def text(string: String): Doc[Nothing]

  /**
    * Generate a document from a single character. Spaces are considered
    * essential.
    */
  protected def char(c: SChar): Doc[Nothing]

  /**
    * A document which represents a linebreak. If the linebreak is eliminated
    * via flattening it will be replaced by its replacement string.
    */
  def line(replacement: String): Doc[Nothing]

  /**
    * Modifies a document so that each linebreak is followed by some amount
    * of whitespace. If there are no linebreaks then `nest(i)` is a no-op:
    * `nest(i)(text(s)) = text(s)`. Furthermore, `nest` witnesses two monoid
    * homomorphisms:
    *
    *     // composition with addition
    *     nest(i) _ andThen nest(j) _ = nest(i + j)(d)
    *     d = identity(d) = nest(0)(d)
    *
    *     // documents to documents
    *     nest(i)(x <> y) = nest(i)(x) <> nest(i)(y)
    *     nest(i)(nil) = nil
    */
  def nest[A](amt: Int)(doc: Doc[A]): Doc[A]

}
