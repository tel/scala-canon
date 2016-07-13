package jspha.canon.ops

import scala.language.higherKinds

trait CharacterConstants[Doc[_]] {
  self: Basic[Doc] =>

  val lparen: Doc[Nothing] = char('(')
  val rparen: Doc[Nothing] = char(')')
  val langle: Doc[Nothing] = char('<')
  val rangle: Doc[Nothing] = char('>')
  val lbrace: Doc[Nothing] = char('{')
  val rbrace: Doc[Nothing] = char('}')
  val lbracket: Doc[Nothing] = char('[')
  val rbracket: Doc[Nothing] = char(']')
  val squote: Doc[Nothing] = char(''')
  val dquote: Doc[Nothing] = char('"')
  val semi: Doc[Nothing] = char(';')
  val colon: Doc[Nothing] = char(':')
  val space: Doc[Nothing] = char(' ')
  val comma: Doc[Nothing] = char(',')
  val doc: Doc[Nothing] = char('.')
  val backslash: Doc[Nothing] = char('\\')
  val equals: Doc[Nothing] = char('=')
  val pipe: Doc[Nothing] = char('|')

}
