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
	 * The CssProperty trait represents a css property identifier.
	 *
	 * @author Joa Ebert
	 */
	sealed trait CssProperty {
		/**
		 * The string representation of the property.
		 */
		def name: String

		/**
		 * The string representation of the current object.
 		 */
		override def toString = name
	}

	class StringBasedProperty(val name: String) extends CssProperty
	
	case object azimuth extends StringBasedProperty("azimuth")
	case object background extends StringBasedProperty("background")
	case object backgroundAttachment extends StringBasedProperty("background-attachment")
	case object backgroundColor extends StringBasedProperty("background-color")
	case object backgroundImage extends StringBasedProperty("background-image")
	case object backgroundPosition extends StringBasedProperty("background-position")
	case object backgroundRepeat extends StringBasedProperty("background-repeat")
	case object border extends StringBasedProperty("border")
	case object borderBottom extends StringBasedProperty("border-bottom")
	case object borderBottomColor extends StringBasedProperty("border-bottom-color")
	case object borderBottomStyle extends StringBasedProperty("border-bottom-style")
	case object borderBottomWidth extends StringBasedProperty("border-bottom-width")
	case object borderCollapse extends StringBasedProperty("border-collapse")
	case object borderColor extends StringBasedProperty("border-color")
	case object borderLeft extends StringBasedProperty("border-left")
	case object borderLeftColor extends StringBasedProperty("border-left-color")
	case object borderLeftStyle extends StringBasedProperty("border-left-style")
	case object borderLeftWidth extends StringBasedProperty("border-left-width")
	case object borderRight extends StringBasedProperty("border-right")
	case object borderRightColor extends StringBasedProperty("border-right-color")
	case object borderRightStyle extends StringBasedProperty("border-right-style")
	case object borderRightWidth extends StringBasedProperty("border-right-width")
	case object borderSpacing extends StringBasedProperty("border-spacing")
	case object borderStyle extends StringBasedProperty("border-style")
	case object borderTop extends StringBasedProperty("border-top")
	case object borderTopColor extends StringBasedProperty("border-top-color")
	case object borderTopStyle extends StringBasedProperty("border-top-style")
	case object borderTopWidth extends StringBasedProperty("border-top-width")
	case object borderWidth extends StringBasedProperty("border-width")
	case object bottom extends StringBasedProperty("bottom")
	case object captionSide extends StringBasedProperty("caption-side")
	case object clear extends StringBasedProperty("clear")
	case object clip extends StringBasedProperty("clip")
	case object color extends StringBasedProperty("color")
	case object content extends StringBasedProperty("content")
	case object counterIncrement extends StringBasedProperty("counter-increment")
	case object counterReset extends StringBasedProperty("counter-reset")
	case object cue extends StringBasedProperty("cue")
	case object cueAfter extends StringBasedProperty("cue-after")
	case object cueBefore extends StringBasedProperty("cue-before")
	case object cursor extends StringBasedProperty("cursor")
	case object direction extends StringBasedProperty("direction")
	case object display extends StringBasedProperty("display")
	case object elevation extends StringBasedProperty("elevation")
	case object emptyCells extends StringBasedProperty("empty-cells")
	case object float extends StringBasedProperty("float")
	case object font extends StringBasedProperty("font")
	case object fontFamily extends StringBasedProperty("font-family")
	case object fontSize extends StringBasedProperty("font-size")
	case object fontStyle extends StringBasedProperty("font-style")
	case object fontVariant extends StringBasedProperty("font-variant")
	case object fontWeight extends StringBasedProperty("font-weight")
	case object height extends StringBasedProperty("height")
	case object left extends StringBasedProperty("left")
	case object letterSpacing extends StringBasedProperty("letter-spacing")
	case object lineHeight extends StringBasedProperty("line-height")
	case object listStyle extends StringBasedProperty("list-style")
	case object listStyleImage extends StringBasedProperty("list-style-image")
	case object listStylePosition extends StringBasedProperty("list-style-position")
	case object listStyleType extends StringBasedProperty("list-style-type")
	case object margin extends StringBasedProperty("margin")
	case object marginBottom extends StringBasedProperty("margin-bottom")
	case object marginLeft extends StringBasedProperty("margin-left")
	case object marginRight extends StringBasedProperty("margin-right")
	case object marginTop extends StringBasedProperty("margin-top")
	case object maxHeight extends StringBasedProperty("max-height")
	case object maxWidth extends StringBasedProperty("max-width")
	case object minHeight extends StringBasedProperty("min-height")
	case object minWidth extends StringBasedProperty("min-width")
	case object orphans extends StringBasedProperty("orphans")
	case object outline extends StringBasedProperty("outline")
	case object outlineColor extends StringBasedProperty("outline-color")
	case object outlineStyle extends StringBasedProperty("outline-style")
	case object outlineWidth extends StringBasedProperty("outline-width")
	case object overflow extends StringBasedProperty("overflow")
	case object padding extends StringBasedProperty("padding")
	case object paddingBottom extends StringBasedProperty("padding-bottom")
	case object paddingLeft extends StringBasedProperty("padding-left")
	case object paddingRight extends StringBasedProperty("padding-right")
	case object paddingTop extends StringBasedProperty("padding-top")
	case object pageBreakAfter extends StringBasedProperty("page-break-after")
	case object pageBreakBefore extends StringBasedProperty("page-break-before")
	case object pageBreakInside extends StringBasedProperty("page-break-inside")
	case object pause extends StringBasedProperty("pause")
	case object pauseAfter extends StringBasedProperty("pause-after")
	case object pauseBefore extends StringBasedProperty("pause-before")
	case object pitch extends StringBasedProperty("pitch")
	case object pitchRange extends StringBasedProperty("pitch-range")
	case object playDuring extends StringBasedProperty("play-during")
	case object position extends StringBasedProperty("position")
	case object quotes extends StringBasedProperty("quotes")
	case object richness extends StringBasedProperty("richness")
	case object right extends StringBasedProperty("right")
	case object scrollbar3DlightColor extends StringBasedProperty("scrollbar-3dlight-color")
	case object scrollbarArrowColor extends StringBasedProperty("scrollbar-arrow-color")
	case object scrollbarBaseColor extends StringBasedProperty("scrollbar-base-color")
	case object scrollbarDarkshadowColor extends StringBasedProperty("scrollbar-darkshadow-color")
	case object scrollbarFaceColor extends StringBasedProperty("scrollbar-face-color")
	case object scrollbarHighlightColor extends StringBasedProperty("scrollbar-highlight-color")
	case object scrollbarShadowColor extends StringBasedProperty("scrollbar-shadow-color")
	case object scrollbarTrackColor extends StringBasedProperty("scrollbar-track-color")
	case object speak extends StringBasedProperty("speak")
	case object speakHeader extends StringBasedProperty("speak-header")
	case object speakNumeral extends StringBasedProperty("speak-numeral")
	case object speakPunctuation extends StringBasedProperty("speak-punctuation")
	case object speechRate extends StringBasedProperty("speech-rate")
	case object stress extends StringBasedProperty("stress")
	case object tableLayout extends StringBasedProperty("table-layout")
	case object textAlign extends StringBasedProperty("text-align")
	case object textDecoration extends StringBasedProperty("text-decoration")
	case object textIndent extends StringBasedProperty("text-indent")
	case object textTransform extends StringBasedProperty("text-transform")
	case object top extends StringBasedProperty("top")
	case object unicodeBidi extends StringBasedProperty("unicode-bidi")
	case object verticalAlign extends StringBasedProperty("vertical-align")
	case object visibility extends StringBasedProperty("visibility")
	case object voiceFamily extends StringBasedProperty("voice-family")
	case object volume extends StringBasedProperty("volume")
	case object whiteSpace extends StringBasedProperty("white-space")
	case object widows extends StringBasedProperty("widows")
	case object width extends StringBasedProperty("width")
	case object wordSpacing extends StringBasedProperty("word-spacing")
	case object zIndex extends StringBasedProperty("z-index")
}