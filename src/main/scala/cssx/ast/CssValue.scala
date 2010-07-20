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
	 * @author Joa Ebert
	 */
	sealed trait CssValue {
		def accept(visitor: CssASTVisitor): Boolean = {
			visitor beginVisit this
			visitor endVisit this
		}
	}

	case class CssPxValue(value: Int) extends CssValue {
		def px = this
		override def toString = value.toString+"px"
	}

	case class CssEmValue(value: Double) extends CssValue {
		def em = this
		override def toString = value.toString+"em"
	}

	case class CssPercentValue(value: Double) extends CssValue {
		def percent = this
		override def toString = value.toString+"%"
	}

	case class CssIntValue(value: Int) extends CssValue {
		override def toString = value.toString
	}
}