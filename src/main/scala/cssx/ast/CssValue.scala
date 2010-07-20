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

		protected def doubleToString(value: Double) = value.toString match {
			case x if x endsWith ".0" => x.substring(0, x.length - 2)
			case other => other
		}
	}

	//
	// Relative length units
	//

	case class CssEmValue(value: Double) extends CssValue {
		def em = this
		override def toString = doubleToString(value)+"em"
	}

	case class CssExValue(value: Double) extends CssValue {
		def ex = this
		override def toString = doubleToString(value)+"ex"
	}
	
	case class CssPxValue(value: Double) extends CssValue {
		def px = this
		override def toString = doubleToString(value)+"px"
	}

	case class CssGdValue(value: Double) extends CssValue {
		def gd = this
		override def toString = doubleToString(value)+"gd"
	}

	case class CssRemValue(value: Double) extends CssValue {
		def rem = this
		override def toString = doubleToString(value)+"rem"
	}

	case class CssVwValue(value: Double) extends CssValue {
		def vw = this
		override def toString = doubleToString(value)+"vw"
	}

	case class CssVhValue(value: Double) extends CssValue {
		def vh = this
		override def toString = doubleToString(value)+"vh"
	}

	case class CssVmValue(value: Double) extends CssValue {
		def vm = this
		override def toString = doubleToString(value)+"vm"
	}

	case class CssChValue(value: Double) extends CssValue {
		def vm = this
		override def toString = doubleToString(value)+"vm"
	}

	//
	// Absolute length units
	//

	case class CssInValue(value: Double) extends CssValue {
		def in = this
		override def toString = doubleToString(value)+"in"
	}

	case class CssCmValue(value: Double) extends CssValue {
		def cm = this
		override def toString = doubleToString(value)+"cm"
	}

	case class CssMmValue(value: Double) extends CssValue {
		def in = this
		override def toString = doubleToString(value)+"mm"
	}

	case class CssPtValue(value: Double) extends CssValue {
		def in = this
		override def toString = doubleToString(value)+"pt"
	}

	case class CssPcValue(value: Double) extends CssValue {
		def in = this
		override def toString = doubleToString(value)+"pc"
	}

	//
	// Percentages
	//

	case class CssPercentValue(value: Double) extends CssValue {
		def percent = this
		override def toString = doubleToString(value)+"%"
	}

	//
	// The URL function
	//

	case class CssUrlValue(value: String) extends CssValue {
		def url = this
		override def toString = "url(\""+value+"\")"
	}

	case class CssHexValue(value: Int) extends CssValue {
		def hex = this
		override def toString = "#"+(value.toHexString  match {
			case x if x.charAt(0) == x.charAt(1) && x.charAt(2) == x.charAt(3) && x.charAt(4) == x.charAt(5) =>
				x.charAt(0).toString+x.charAt(2).toString+x.charAt(4).toString
			case other => other
		})
	}
}