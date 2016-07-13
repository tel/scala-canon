package jspha.canon

                              /*

  From [1]:

      Next, we need to specify how to choose the best layout among all those
      in a set. Following Hughes, we do so by specifying an ordering relation
      between lines, and extending this lexically to an ordering between
      documents.

      The ordering relation depends on the available width. If both lines are
      shorter than the available width, the longer one is better. If one line
      fits in the available width and the other does not, the one that fits
      is better. If both lines are longer than the available width, the
      shorter one is better. Note that this ordering relation means that we
      may sometimes pick a layout where some line exceeds the given width,
      but we will do so only if this is unavoidable.

      ...

      To achieve acceptable performance, we will exploit the distributive
      law, and use the representation (s ‘Text‘ (x ‘Union‘ y)) in preference
      to the equivalent ((s ‘Text‘ x) ‘Union‘ (s ‘Text‘ y)).

                               */

/**
  * A `Document` represents a *set* of "acceptable" strings varying only in
  * "non-essential" whitespace: imagine an operation `flatten(s)` which takes
  * a string and eliminates all "non-essential" whitespace, each string in a
  * `Document` set must `flatten` to the same value.
  *
  * A `Document[A]` additionally carries "annotations" of type `A`. An
  * annotation is a value attached to one region of each string in the
  * `Document`-set such that all the regions correspond. You can imagine
  * annotations as providing an XML-like tag structure on the strings in the
  * set and with the natural extension of `flatten` to these XML structures,
  * all of the strings in the `Document`-set must `flatten` to the same XML
  * structure.
  *
  * Core API is taken from Wadler's "A Prettier Printer" [1] and from Daan
  * Leijen's practical extensions thereof [2]. Each of these have an
  * inefficient layout method in strict languages, so the efficient
  * intepreter is taken from Lindig's "Strictly Pretty" [3]. Ultimately, the
  * API follows Wadler's terminology most closely.
  *
  * [1] http://homepages.inf.ed.ac.uk/wadler/papers/prettier/prettier.pdf
  * [2] https://hackage.haskell.org/package/annotated-wl-pprint
  * [3] http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.34.2200&rep=rep1&type=pdf
  */

// TODO: Not yet sealed while in development so we can have multiple impls.
trait Document[+A] {
  import Document._

//  /**
//    * Catenate two documents directly: `layout(x <> y) = layout(x) +
//    * layout(y)`
//    */
//  def <>[AA >: A](other: Document[AA]): Document[AA] =
//    cat(this, other)

}

object Document
//  extends ops.Basic[Document]
//    with ops.Grouping[Document]
//    with ops.Derived[Document]
//    with ops.Unsafe[Document]
//    with ops.CharacterConstants[Document]

