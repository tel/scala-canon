package jspha.canon.ops

import scala.language.higherKinds
import scala.{Char => SChar}

private[canon] trait Unsafe[Doc[_]] {
  self: Basic[Doc] =>

  object Unsafe {

    /**
      * Directly creates a document from a string considering all whitespace
      * essential.
      */
    def text(s: String): Doc[Nothing] =
      self.text(s)

    /**
      * Directly creates a document from a character considering all whitespace
      * essential.
      */
    def char(c: SChar): Doc[Nothing] =
      self.char(c)
  }

}
