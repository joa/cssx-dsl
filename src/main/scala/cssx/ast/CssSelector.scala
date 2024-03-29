package cssx.ast {
	/**
	 * @author Joa Ebert
	 */
	sealed trait CssSelector {
		def accept(visitor: CssASTVisitor): Boolean = {
			visitor beginVisit this
			visitor endVisit this
		}

		private def mkList(x: List[CssSelector]): CssSelector = {
			def loop(y: CssSelector, ys: List[CssSelector]): CssSelector = ys match {
				case x :: Nil => CssSelectorList(y, x)
				case x :: xs => CssSelectorList(y, loop(x, xs))
				case Nil => error("Unreachable by definition.")
			}

			x match {
				case x :: Nil => x
				case x :: xs => loop(x, xs)
				case Nil => error("Given list is empty.")
			}
		}

		private def crossProduct(x: CssSelector, y: CssSelector)(op: (CssSelector, CssSelector) =>
				CssSelector): CssSelector =  mkList(for(a <- x.toList; b <- y.toList) yield op(a, b))

		def name: String
		def toList: List[CssSelector]

		override def toString = name

		def and(that: CssSelector) = this & that
		def or(that: CssSelector) = this | that

		def withClass(that: String) = this ~% that
		def withDescendant(that: CssSelector) = this ~/ that
		def withChild(that: CssSelector) = this ~> that
		def withImmediateSuccessor(that: CssSelector) = this ~+ that
		def withSuccessor(that: CssSelector) = this ~~ that
		def withId(that: String) = this ~# that

		/**
		 * <code>E & F</code> equals <code>E, F</code>.
		 */
		def &(that: CssSelector) = CssSelectorList(this, that)
		def |(that: CssSelector) = this & that

		/**
		 * <code>E ~% "warning"</code> equals <code>E.warning</code>.
		 */
		def ~%(that: String) = mkList(this.toList map { x => CssClassSelector(Some(x), that) })

		/**
		 * <code>E ~/ F</code> equals <code>E F</code>.
		 */
		def ~/(that: CssSelector) = crossProduct(this, that) { CssDescendantSelector(_, _) }

		/**
		 * <code>E ~> F</code> equals <code>E > F</code>.
		 */
		def ~>(that: CssSelector) = crossProduct(this, that) { CssChildSelector(_, _) }

		/**
		 * <code>E ~+ F</code> equals <code>E + F</code>.
		 */
		def ~+(that: CssSelector) = crossProduct(this, that) { CssImmediatelyPrecededSelector(_, _) }

		/**
		 * <code>E ~~ F</code> equals <code>E ~ F</code>.
		 */
		def ~~(that: CssSelector) = crossProduct(this, that) { CssPrecededSelector(_, _) }

		/**
		 * <code>E ~# "myid"</code> equals <code>E#myid</code>.
		 */
		def ~#(that: String) = mkList(this.toList map { x => CssIdSelector(Some(x), that) })
	}

	case class CssChildSelector(parent: CssSelector, child: CssSelector) extends CssSelector {
		override def name = parent.toString+">"+child.toString
		override def toList = this :: Nil
	}

	case class CssImmediatelyPrecededSelector(predecessor: CssSelector, successor: CssSelector) extends CssSelector {
		override def name = predecessor.toString+"+"+successor.toString
		override def toList = this :: Nil
	}

	case class CssPrecededSelector(predecessor: CssSelector, successor: CssSelector) extends CssSelector {
		override def name = predecessor.toString+"~"+successor.toString
		override def toList = this :: Nil
	}

	case class CssDescendantSelector(parent: CssSelector, descendant: CssSelector) extends CssSelector {
		override def name = parent.toString+" "+descendant.toString
		override def toList = this :: Nil
	}

	case class CssSelectorList(a: CssSelector, b: CssSelector) extends CssSelector {
		override def name = a.toString+","+b.toString
		override def toList = a.toList ::: b.toList
	}

	case class CssClassSelector(prefix: Option[CssSelector], `class`: String) extends CssSelector {
		override def name = prefix match {
			case Some(p) => p.toString+"."+`class`
			case None => "."+`class`
		}

		override def toList = this :: Nil
	}

	case class CssIdSelector(prefix: Option[CssSelector], id: String) extends CssSelector {
		override def name = prefix match {
			case Some(p) => p.toString+"#"+id
			case None => "#"+id
		}

		override def toList = this :: Nil
	}
	
	class StringBasedSelector(val name: String) extends CssSelector {
		override def toList = this :: Nil
	}

	case object `*` extends StringBasedSelector("*")
	case object any extends StringBasedSelector("*")
	
	case object a extends StringBasedSelector("a")
	case object abbr extends StringBasedSelector("abbr")
	case object acronym extends StringBasedSelector("acronym")
	case object address extends StringBasedSelector("address")
	case object applet extends StringBasedSelector("applet")
	case object area extends StringBasedSelector("area")
	case object b extends StringBasedSelector("b")
	case object base extends StringBasedSelector("base")
	case object basefont extends StringBasedSelector("basefont")
	case object bdo extends StringBasedSelector("bdo")
	case object big extends StringBasedSelector("big")
	case object blockquote extends StringBasedSelector("blockquote")
	case object body extends StringBasedSelector("body")
	case object br extends StringBasedSelector("br")
	case object button extends StringBasedSelector("button")
	case object caption extends StringBasedSelector("caption")
	case object center extends StringBasedSelector("center")
	case object cite extends StringBasedSelector("cite")
	case object code extends StringBasedSelector("code")
	case object col extends StringBasedSelector("col")
	case object colgroup extends StringBasedSelector("colgroup")
	case object dd extends StringBasedSelector("dd")
	case object del extends StringBasedSelector("del")
	case object dfn extends StringBasedSelector("dfn")
	case object dir extends StringBasedSelector("dir")
	case object div extends StringBasedSelector("div")
	case object dl extends StringBasedSelector("dl")
	case object dt extends StringBasedSelector("dt")
	case object em extends StringBasedSelector("em")
	case object fieldset extends StringBasedSelector("fieldset")
	case object font_ extends StringBasedSelector("font")
	case object form extends StringBasedSelector("form")
	case object frame extends StringBasedSelector("frame")
	case object frameset extends StringBasedSelector("frameset")
	case object head extends StringBasedSelector("head")
	case object h1 extends StringBasedSelector("h1")
	case object h2 extends StringBasedSelector("h2")
	case object h3 extends StringBasedSelector("h3")
	case object h4 extends StringBasedSelector("h4")
	case object h5 extends StringBasedSelector("h5")
	case object h6 extends StringBasedSelector("h6")
	case object hr extends StringBasedSelector("hr")
	case object html extends StringBasedSelector("html")
	case object i extends StringBasedSelector("i")
	case object iframe extends StringBasedSelector("iframe")
	case object img extends StringBasedSelector("img")
	case object input extends StringBasedSelector("input")
	case object ins extends StringBasedSelector("ins")
	case object kbd extends StringBasedSelector("kbd")
	case object label extends StringBasedSelector("label")
	case object legend extends StringBasedSelector("legend")
	case object li extends StringBasedSelector("li")
	case object link extends StringBasedSelector("link")
	case object map extends StringBasedSelector("map")
	case object menu extends StringBasedSelector("menu")
	case object meta extends StringBasedSelector("meta")
	case object noframes extends StringBasedSelector("noframes")
	case object noscript extends StringBasedSelector("noscript")
	case object `object` extends StringBasedSelector("object")
	case object ol extends StringBasedSelector("ol")
	case object optgroup extends StringBasedSelector("optgroup")
	case object option extends StringBasedSelector("option")
	case object p extends StringBasedSelector("p")
	case object param extends StringBasedSelector("param")
	case object pre extends StringBasedSelector("pre")
	case object q extends StringBasedSelector("q")
	case object s extends StringBasedSelector("s")
	case object samp extends StringBasedSelector("samp")
	case object script extends StringBasedSelector("script")
	case object select extends StringBasedSelector("select")
	case object small extends StringBasedSelector("small")
	case object span extends StringBasedSelector("span")
	case object strike extends StringBasedSelector("strike")
	case object strong extends StringBasedSelector("strong")
	case object style extends StringBasedSelector("style")
	case object sub extends StringBasedSelector("sub")
	case object sup extends StringBasedSelector("sup")
	case object table extends StringBasedSelector("table")
	case object tbody extends StringBasedSelector("tbody")
	case object td extends StringBasedSelector("td")
	case object textarea extends StringBasedSelector("textarea")
	case object tfoot extends StringBasedSelector("tfoot")
	case object th extends StringBasedSelector("th")
	case object thead extends StringBasedSelector("thead")
	case object title extends StringBasedSelector("title")
	case object tr extends StringBasedSelector("tr")
	case object tt extends StringBasedSelector("tt")
	case object u extends StringBasedSelector("u")
	case object ul extends StringBasedSelector("ul")
	case object `var` extends StringBasedSelector("var")
}