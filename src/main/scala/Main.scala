import cssx.ast._
import cssx.Css

/**
 * @author Joa Ebert
 */
object Main {
	def main(args: Array[String]) {
		// Simple example of how to use CSSX
		// & is the ", " conjuction
		// a##b selects by identifier b ("div##b" equals "div#b")
		// ##(b) selects the identifier b ("##(b)" equals "#b")
		// a>b selects the child b of a ("a>b" equals "a>b")
		// a { b { ... } } is the desendant selector ("a { b { ... } }" equals "a b { ... }")
		
		val css = new Css {
			// This is equivalent to the following CSS selector:
			// div > div#identifier.class0 > div, div > div.class1 > div
			div > (div##"identifier"%"class0" & div%"class1") > div is {
				width ~ 100.percent

				// This resulits in the crossproduct of the selectors
				// (div > div#identifier.class0 > div, div > div.class1 > div)
				// and (div, span). It is equivalent to the following CSS selector:
				//
				// div > div#identifier.class0 > div div
				// div > div#identifier.class0 > div span
				// div > div.class1 > div div
				// div > div.class1 > div span
				div & span is {
					h1 & h2 is {
						width ~ 1.em
					}

					width ~ 256.px
				}

				h1 & h2 is {
					width ~ 2.em
				}
			}

			##("id") is {
				width ~ 100.percent
				height ~ 100.percent
			}
		}

		println(css.toAST)
	}
}