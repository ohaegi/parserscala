package parser

sealed abstract class CharParser
case class CharParserSep(value: Char) extends CharParser
case class CharParserNormal(value: Char) extends CharParser
