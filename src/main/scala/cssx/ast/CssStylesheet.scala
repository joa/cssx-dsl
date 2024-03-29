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
	 * The CssStylesheet class represents the toplevel element of the Css AST.
	 * 
	 * @author Joa Ebert
	 */
	case class CssStylesheet(statements: List[CssStatement]) {
		override def toString = statements mkString "\n"

		def accept(visitor: CssASTVisitor): Boolean = {
			if(visitor beginVisit this) {
				for(statement <- statements) {
					if(!(statement accept visitor)) {
						return (visitor endVisit this)
					}
				}
			}
			
			visitor endVisit this
		}
	}
}