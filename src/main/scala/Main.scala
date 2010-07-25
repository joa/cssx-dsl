import cssx.ast._
import cssx.Css

/**
 * @author Joa Ebert
 */
object Main {
	def main(args: Array[String]) {
		/*val css = new Css {
			div ~> (div~#"identifier"~%"class0" & div~%"class1") ~> div is {
				width := 100.percent

				div & span is {
					h1 & h2 is {
						width := 1.em
						padding := 0.px
					}

					backgroundColor := 0x333333.hex
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
		}*/


		val css = new Css {
			input is {
				width := 960.px
			}

			div ~# "meinforumlar" is {
				input is {
					padding := 1.px ~ 2.px ~ 3.px ~ 4.px
					width := 200.px
				}
			}
		}

		println("---NORMAL---")
		css.toAST.accept(new CssPrintVisitor(Console.out))
		println("---COMPRESSED---")
		css.toAST.accept(new CssPrintVisitor(Console.out, true))
	}
}