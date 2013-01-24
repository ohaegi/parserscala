package parser

abstract class AbstractParsing {
  def charSepParser(value: Char): CharParser
  
}
class NaturalParsing extends AbstractParsing {
  def charSepParser(value: Char): CharParser =
    value match {
      case ' ' | '\t' | '\n' | '\r' => CharParserSep(value)
      case _ => CharParserNormal(value)
    }
}