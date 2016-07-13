
# Canon

Pretty printing of documents.

# Sources

- [*A prettier printer* by Philip Wadler](http://homepages.inf.ed.ac.uk/wadler/papers/prettier/prettier.pdf)
  improves upon Hughes' original algebraic pretty printer idea
  both simplifying and improving efficiency.
- [*Strictly Pretty* by Christian Lindig](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.34.2200&rep=rep1&type=pdf)
  fixes large performance issues that arise when Wadler's formulation 
  is applied in a strict language.
- [The `wl-pprint` package](https://hackage.haskell.org/package/wl-pprint-1.2/docs/Text-PrettyPrint-Leijen.html)
  extends Wadler's basic formulation with a number of extra 
  combinators which are very useful for formulating many useful 
  document types.
- [The `annotated-wl-pprint` package](https://hackage.haskell.org/package/annotated-wl-pprint) 
  by Daan Leijen and David Christiansen extends `wl-pprint` package 
  with annotated sections.