package parser



abstract class AbstractParsing {
  def listTokensParserDef() : List[LexerDef]  
}
class NaturalParsing extends AbstractParsing {
  def listTokensParserDef() : List[LexerDef] =
    List(
        new LexerDefChars("space", List(' ','\t','\n','\r'))
        )
}