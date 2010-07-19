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
package cssx.ast {
	/**
	 * The CssStatement trait represents a css statement.
	 * 
	 * @author Joa Ebert
	 */
	sealed trait CssStatement

	/**
	 * The CssRuleset class represents a css ruleset.
	 *
	 * A Css ruleset consists of a selector and a list of declarations which
	 * apply to the element matched by the selector.
	 * 
	 * @author Joa Ebert
	 */
	case class CssRuleset(selector: CssSelector, declarations: List[CssDeclaration]) extends CssStatement {
		override def toString = selector.toString+"{"+(declarations mkString ";")+"}"
	}
}