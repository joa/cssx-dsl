CSS DSL for the [Scala](http://www.scala-lang.org) programming language.

```
println(new Css {
	// This is equivalent to the following CSS selector:
	// div > div#identifier.class0 > div, div > div.class1 > div
	div ~> (div ~# "identifier" ~% "class0" & div ~% "class1") ~> div is {
		width := 100.percent

		// This resulits in the crossproduct of the selectors
		// (div > div#identifier.class0 > div, div > div.class1 > div)
		// and (div, span). It is equivalent to the following CSS selector:
		//
		// div > div#identifier.class0 > div div,
		// div > div#identifier.class0 > div span,
		// div > div.class1 > div div,
		// div > div.class1 > div span
		div & span is {
                        // Again a crossproduct.
			h1 & h2 is {
				width := 1.em
			}
			width := 256.px
		}
		h1 & h2 is {
			width := 2.em
		}
	}
	$("id") is {
		width := 100.percent
		height := 100.percent
	}
}.toAST)
```

```
div>div#identifier.class0>div,div>div.class1>div{width:100.0%}
div>div#identifier.class0>div div,div>div#identifier.class0>div span,div>div.class1>div div,div>div.class1>div span{width:256px}
div>div#identifier.class0>div div h1,div>div#identifier.class0>div div h2,div>div#identifier.class0>div span h1,div>div#identifier.class0>div span h2,div>div.class1>div div h1,div>div.class1>div div h2,div>div.class1>div span h1,div>div.class1>div span h2{width:1.0em}
div>div#identifier.class0>div h1,div>div#identifier.class0>div h2,div>div.class1>div h1,div>div.class1>div h2{width:2.0em}
#id{width:100.0%;height:100.0%}
```