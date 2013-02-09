package parser
import scala.util.matching.Regex

class LexerDefRegExp(tokenType: String, regexp: Regex) extends LexerDef(tokenType) {
  override def tokenize(value: String, previous: Option[Token]): Option[Token] = {
    val regMatch = regexp.findFirstMatchIn(value)
    if (regMatch.isEmpty) {
      None
    } else {
      val beforeToken = Some(new TokenUnTyped(regMatch.get.before.toString(), previous))
      val matchedToken = Some(new TokenTyped(regMatch.get.before.toString(), beforeToken, tokenType))
      val afterToken = Some(new TokenUnTyped(regMatch.get.after.toString(), matchedToken))
      beforeToken
    }
  }
}

