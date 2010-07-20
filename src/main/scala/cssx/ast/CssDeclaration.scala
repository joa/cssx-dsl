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
	 * The CssDeclaration trait represents a css declaration.
	 * 
	 * @author Joa Ebert
	 */
	case class CssDeclaration(
			/**
			 * The property of the declaration.
			 */
			property: CssProperty,
			/**
			 * The value of the declaration.
			 */
			value: CssValue) {

		/**
		 * The string representation of the current object.
		 */
		override def toString = property.toString+":"+value.toString

		def accept(visitor: CssASTVisitor): Boolean = {
			if(visitor beginVisit this) {
				if(property accept visitor) {
					value accept visitor	
				}
			}

			visitor endVisit this
		}
	}
}