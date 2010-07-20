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

import collection.mutable.Stack

/**
 * @author Joa Ebert
 */
class CssPrintVisitor(writer: { def flush(): Unit; def print(value: String): Unit}, omitSpaces: Boolean = false) extends CssASTVisitor {
	private var stack = new Stack[CssRuleset]()
	private val space = if(omitSpaces) "" else " "
	private val newLine = if(omitSpaces) "" else "\n"
	private var indent: Int = 0

	private def push(value: CssRuleset) = {
		stack push value
		indent += 2
	}

	private def pop() = {
		stack.pop()
		indent -= 2
	}

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
		if(value.declarations.nonEmpty) {
			writer print indentStr+(if(omitSpaces) value.selector.toString else selectorToString(value.selector))+space+"{"+newLine
			push(value)
			true
		} else {
			false
		}
	}

	override def endVisit(value: CssRuleset): Boolean = {
		if(value.declarations.nonEmpty) {
			pop()
			writer print indentStr+"}"+newLine+newLine
		}
		
		true
	}

	override def beginVisit(value: CssSelector): Boolean = true
	override def endVisit(value: CssSelector): Boolean = true

	override def beginVisit(value: CssDeclaration): Boolean = {
		writer print indentStr
		true
	}
	
	override def endVisit(value: CssDeclaration): Boolean = {
		if(stack.nonEmpty) {
			if(stack.top.declarations.last == value) {
				writer print newLine
			} else {
				writer print ";"+newLine
			}
		} else {
			writer print ";"+newLine
		}
		
		true
	}

	override def beginVisit(value: CssProperty): Boolean = {
		writer print value.toString+":"+space
		true
	}
	
	override def endVisit(value: CssProperty): Boolean = true

	override def beginVisit(value: CssValue): Boolean = {
		writer.print(value match {
			case CssPxValue(v) => if(omitSpaces) { doubleToString(v) } else { doubleToString(v)+"px" }
			case o => o.toString
		})
		true
	}
	
	override def endVisit(value: CssValue): Boolean = { true }

	private def doubleToString(value: Double) = value.toString match {
		case x if x endsWith ".0" => x.substring(0, x.length - 2)
		case other => other
	}

	private def selectorToString(selector: CssSelector): String = selector match {
		case list: CssSelectorList => (list.toList map selectorToString).mkString(","+newLine)
		case CssChildSelector(parent, child) => selectorToString(parent)+space+">"+space+selectorToString(child)
		case CssImmediatelyPrecededSelector(pred, succ) => selectorToString(pred)+space+"+"+space+selectorToString(succ)
		case CssPrecededSelector(pred, succ) => selectorToString(pred)+space+"~"+space+selectorToString(succ)
		case CssDescendantSelector(parent, descendant) => selectorToString(parent)+" "+selectorToString(descendant)
		case CssClassSelector(pre, klass) => (pre map selectorToString getOrElse "")+"."+klass
		case CssIdSelector(pre, id) => (pre map selectorToString getOrElse "")+"#"+id
		case stringBased: StringBasedSelector => stringBased.name
	}
}