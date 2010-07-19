import cssx.ast._
import cssx.Css

/**
 * @author Joa Ebert
 */
object Main {
	def main(args: Array[String]) {
		//simple example of how to use cssx
		
		val x = new Css {
			div > (div%"test" & div%"test2") > h1 is {
				width ~ 256.px

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
		}

		println(x.toAST)
	}
}