package jspha.canon.ops

import scala.language.higherKinds

private[canon] trait Derived[Doc[_]] {
  self: Basic[Doc] =>
}
