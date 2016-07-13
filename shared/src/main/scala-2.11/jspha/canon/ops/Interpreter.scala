package jspha.canon.ops

import scala.language.higherKinds

private[canon] trait Interpreter[Doc[_], X[_]] {

//  /**
//    * Prints a document as simply as possible. More or less equivalent to
//    * `pretty(infinity, _)`.
//    */
//  def layout[A](doc: Doc[A]): X[A]

  /**
    * Prints a document maintaining as much as possible that the total width
    * of any line not go above `width`.
    */
  def pretty[A](width: Int, doc: Doc[A]): X[A]
}
