package parser
import scala.util.matching.Regex

class LexerParserDefStr(tokenType: String, value: String) extends LexerDef(tokenType)
class LexerParserDefStartEnd(tokenType: String, startStr: String, endStr: String) extends LexerDef(tokenType)
class LexerParserDefStartEndRegexp(tokenType: String, startStr: Regex, endStr: Regex) extends LexerDef(tokenType)


