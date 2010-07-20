/*
 * This file is part of Apparat.
 *
 * Apparat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Apparat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Apparat. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010 Joa Ebert
 * http://www.joa-ebert.com/
 *
 */
package cssx {
	import cssx.ast._
	import scala.collection.mutable.{Stack, ListBuffer}

	/**
	 * @author Joa Ebert
	 */
	class Css {
		var parent: Option[Css] = None
		var rulesets = ListBuffer.empty[Ruleset]
		var declarations = ListBuffer.empty[Declaration]
		var current = Stack(this)

		private def newDeclaration(property: CssProperty): Declaration = {
			val decl = new Declaration(property)
			current.top.declarations += decl
			decl
		}

		private def newRuleset(selector: CssSelector): Ruleset = {
			val ruleset = new Ruleset(selector)
			ruleset.parent = Some(this)
			current.top.rulesets += ruleset
			current push ruleset
			ruleset
		}

		def toAST: CssStylesheet = CssStylesheet(rulesets flatMap { _.toCssRuleset } toList)

		implicit def withDeclaration(value: CssProperty): Declaration = newDeclaration(value)
		implicit def withRuleset(value: CssSelector): Ruleset = newRuleset(value)

		implicit def intToPx(value: Int) = CssPxValue(value)
		implicit def intToColor(value: Int) = CssHexValue(value)
		implicit def intToEm(value: Int) = doubleToEm(value.toDouble)
		implicit def doubleToEm(value: Double) = CssEmValue(value)
		implicit def intToPercent(value: Int) = doubleToPercent(value.toDouble)
		implicit def doubleToPercent(value: Double) = CssPercentValue(value)

		def $(id: String) = CssIdSelector(None, id)
		def rgb(red: Int, green: Int, blue: Int) = CssHexValue((red << 0x10) | (green << 0x08) | blue)
	}

	final class Declaration(val property: CssProperty) {
		var value: Option[CssValue] = None
		def :=(value: CssValue) = this.value = Some(value)
		def toCssDeclaration = value match {
			case Some(v) => CssDeclaration(property, v) :: Nil
			case None => Nil
		}
	}

	final class Ruleset(val selector: CssSelector) extends Css {
		def is[T](x: => T) = {
			x; parent match {
				case Some(p) => p.current.pop()
				case None =>
			}
		}

		def toCssRuleset: List[CssRuleset] = {
			def loop(y: CssSelector, ys: List[CssSelector]): CssSelector = ys match {
				case x :: Nil => CssSelectorList(y, x)
				case x :: xs => CssSelectorList(y, loop(x, xs))
				case Nil => error("Unreachable by definition.")
			}

			CssRuleset(selector, declarations flatMap { _.toCssDeclaration } toList) :: { rulesets flatMap {
				_.toCssRuleset } map { x => CssRuleset(
					(for{ a <- selector.toList
						b <- x.selector.toList
					} yield CssDescendantSelector(a, b)) match {
						case x :: Nil => x
						case x :: xs => loop(x, xs)
						case Nil => error("Empty selector.")
					},
					x.declarations) } }.toList
		}
	}
}