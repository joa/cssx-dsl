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

	object Css {
		implicit def intToCh(value: Int) = doubleToCh(value.toDouble)
		implicit def intToCm(value: Int) = doubleToCm(value.toDouble)
		implicit def intToEm(value: Int) = doubleToEm(value.toDouble)
		implicit def intToEx(value: Int) = doubleToEx(value.toDouble)
		implicit def intToGd(value: Int) = doubleToGd(value.toDouble)
		implicit def intToHex(value: Int) = CssHexValue(value)
		implicit def intToIn(value: Int) = doubleToIn(value.toDouble)
		implicit def intToMm(value: Int) = doubleToMm(value.toDouble)
		implicit def intToPc(value: Int) = doubleToPc(value.toDouble)
		implicit def intToPercent(value: Int) = doubleToPercent(value.toDouble)
		implicit def intToPt(value: Int) = doubleToPt(value.toDouble)
		implicit def intToPx(value: Int) = doubleToPx(value.toDouble)
		implicit def intToRem(value: Int) = doubleToRem(value.toDouble)
		implicit def intToVh(value: Int) = doubleToVh(value.toDouble)
		implicit def intToVm(value: Int) = doubleToVm(value.toDouble)
		implicit def intToVw(value: Int) = doubleToVw(value.toDouble)
		implicit def doubleToCh(value: Double) = CssChValue(value)
		implicit def doubleToCm(value: Double) = CssCmValue(value)
		implicit def doubleToEm(value: Double) = CssEmValue(value)
		implicit def doubleToEx(value: Double) = CssExValue(value)
		implicit def doubleToGd(value: Double) = CssGdValue(value)
		implicit def doubleToIn(value: Double) = CssInValue(value)
		implicit def doubleToMm(value: Double) = CssMmValue(value)
		implicit def doubleToPc(value: Double) = CssPcValue(value)
		implicit def doubleToPercent(value: Double) = CssPercentValue(value)
		implicit def doubleToPt(value: Double) = CssPtValue(value)
		implicit def doubleToPx(value: Double) = CssPxValue(value)
		implicit def doubleToRem(value: Double) = CssRemValue(value)
		implicit def doubleToVh(value: Double) = CssVhValue(value)
		implicit def doubleToVm(value: Double) = CssVmValue(value)
		implicit def doubleToVw(value: Double) = CssVwValue(value)
	}

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
		
		def $(id: String) = CssIdSelector(None, id)
		def rgb(red: Int, green: Int, blue: Int) = CssHexValue((red << 0x10) | (green << 0x08) | blue)
		def url(value: String) = CssUrlValue(value)
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