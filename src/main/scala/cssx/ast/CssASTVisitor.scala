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
 * Copyright (C) 2009 Joa Ebert
 * http://www.joa-ebert.com/
 *
 */
package cssx.ast {

	/**
	 * @author Joa Ebert
	 */
	trait CssASTVisitor {
		def beginVisit(value: CssStylesheet): Boolean
		def endVisit(value: CssStylesheet): Boolean

		def beginVisit(value: CssStatement): Boolean
		def endVisit(value: CssStatement): Boolean

		def beginVisit(value: CssRuleset): Boolean
		def endVisit(value: CssRuleset): Boolean

		def beginVisit(value: CssSelector): Boolean
		def endVisit(value: CssSelector): Boolean

		def beginVisit(value: CssDeclaration): Boolean
		def endVisit(value: CssDeclaration): Boolean

		def beginVisit(value: CssProperty): Boolean
		def endVisit(value: CssProperty): Boolean

		def beginVisit(value: CssValue): Boolean
		def endVisit(value: CssValue): Boolean
	}
}