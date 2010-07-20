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
package cssx.ast

import java.io.{PrintWriter => JPrintWriter}

/**
 * @author Joa Ebert
 */
class CssPrintVisitor(writer: { def flush(): Unit; def print(value: String): Unit}, omitSpaces: Boolean = false) extends CssASTVisitor {
	private val space = if(omitSpaces) "" else " "
	private val newLine = if(omitSpaces) "" else "\n"
	private var indent: Int = 0

	private def push() = indent += 2
	private def pop() = indent -= 2

	private def indentStr: String = if(omitSpaces) "" else {
		val sb = new StringBuilder(indent)
		var i = 0
		while(i < indent) {
			sb append ' '
			i += 1
		}
		sb.toString
	}

	override def beginVisit(value: CssStylesheet): Boolean = true
	override def endVisit(value: CssStylesheet): Boolean = {
		writer.flush()
		true
	}

	override def beginVisit(value: CssStatement): Boolean = true
	override def endVisit(value: CssStatement): Boolean = true

	override def beginVisit(value: CssRuleset): Boolean = {
		writer print indentStr+value.selector.toString+space+"{"+newLine
		push()
		true
	}

	override def endVisit(value: CssRuleset): Boolean = {
		pop()
		writer print indentStr+"}"+newLine
		true
	}

	override def beginVisit(value: CssSelector): Boolean = true
	override def endVisit(value: CssSelector): Boolean = true

	override def beginVisit(value: CssDeclaration): Boolean = {
		writer print indentStr
		true
	}
	
	override def endVisit(value: CssDeclaration): Boolean = {
		writer print newLine
		true
	}

	override def beginVisit(value: CssProperty): Boolean = {
		writer print value.toString+":"+space
		true
	}
	override def endVisit(value: CssProperty): Boolean = true

	override def beginVisit(value: CssValue): Boolean = {
		writer print value.toString+";"
		true
	}
	
	override def endVisit(value: CssValue): Boolean = { true }
}