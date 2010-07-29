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
	import Css._
	import ast._
	import org.specs.SpecificationWithJUnit

	/**
	 * @author Joa Ebert
	 */
	class CssxSpec extends SpecificationWithJUnit {
		"Css DSL" should {
			"create an empty AST without any other syntax" in {
				new Css().toAST must beLike { case CssStylesheet(Nil) => true }
			}

			"keep empty selectors in AST" in {
				new Css() {
					div is {
						//empty
					}
				}.toAST must beLike { case CssStylesheet(CssRuleset(_, _) :: _) => true }
			}
		}

		"Css DSL" can {
			"implicitly convert" in {
				"to ch" in {
					new Css() {
						`*` is { fontSize := 1.ch }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssChValue(_)) :: _) :: _) => true }
				}

				"to cm" in {
					new Css() {
						`*` is { fontSize := 1.cm }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssCmValue(_)) :: _) :: _) => true }
				}

				"to em" in {
					new Css() {
						`*` is { fontSize := 1.em }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssEmValue(_)) :: _) :: _) => true }
				}

				"to ex" in {
					new Css() {
						`*` is { fontSize := 1.ex }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssExValue(_)) :: _) :: _) => true }
				}

				"to gd" in {
					new Css() {
						`*` is { fontSize := 1.gd }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssGdValue(_)) :: _) :: _) => true }
				}

				"to hex" in {
					new Css() {
						`*` is { color := 0x333333.hex }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssHexValue(0x333333)) :: _) :: _) => true }
				}

				"to in" in {
					new Css() {
						`*` is { fontSize := 1.in }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssInValue(_)) :: _) :: _) => true }
				}

				"to mm" in {
					new Css() {
						`*` is { fontSize := 1.mm }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssMmValue(_)) :: _) :: _) => true }
				}

				"to pc" in {
					new Css() {
						`*` is { fontSize := 1.pc }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssPcValue(_)) :: _) :: _) => true }
				}

				"to percent" in {
					new Css() {
						`*` is { width := 100.percent }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssPercentValue(_)) :: _) :: _) => true }
				}

				"to pt" in {
					new Css() {
						`*` is { fontSize := 1.pt }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssPtValue(_)) :: _) :: _) => true }
				}

				"to px" in {
					new Css() {
						`*` is { fontSize := 1.px }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssPxValue(_)) :: _) :: _) => true }
				}

				"to rem" in {
					new Css() {
						`*` is { fontSize := 1.rem }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssRemValue(_)) :: _) :: _) => true }
				}

				"to vh" in {
					new Css() {
						`*` is { fontSize := 1.vh }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssVhValue(_)) :: _) :: _) => true }
				}

				"to vm" in {
					new Css() {
						`*` is { fontSize := 1.vm }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssVmValue(_)) :: _) :: _) => true }
				}

				"to vw" in {
					new Css() {
						`*` is { fontSize := 1.vw }
					}.toAST must beLike {
						case CssStylesheet(CssRuleset(_, CssDeclaration(_, CssVwValue(_)) :: _) :: _) => true }
				}
			}
		}
	}
}