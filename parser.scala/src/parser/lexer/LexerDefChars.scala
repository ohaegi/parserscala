package parser
import scala.util.matching.Regex

class LexerDefChars(tokenType: String, chars: List[Char]) extends LexerDef(tokenType) {

  
  override def tokenize(value: Option[TokenUnTyped]): Option[Token] = {
    tokenize(value.get.value, value.get.previous)
  }

  override def tokenize(value: String, previous: Option[Token]): Option[Token] = {
    tokenize(value.iterator, previous)
  }

  override def tokenize(value: Iterator[Char], previous: Option[Token]): Option[Token] = {
    var strMatch: Option[String] = None
    var strOther: Option[String] = None
    var current: Option[Token] = None
    var previousAux: Option[Token] = previous
    var mainToken: Option[Token] = None
    while (value.hasNext) {
      val c = value.next()
      if (chars.contains(c)) {
        strMatch = Some(strMatch.getOrElse("") + c.toString())
        if (strOther.isDefined) {
          current = Some(new TokenUnTyped(strOther.get, previousAux))
          strOther = None
          previousAux = current
        }
      } else {
        strOther = Some(strOther.getOrElse("") + c.toString())
        if (strMatch.isDefined) {
          current = Some(new TokenTyped(strMatch.get, previousAux, "space"))
          strMatch = None
          previousAux = current
        }
      }

      // Keep the main token
      if (mainToken.isEmpty && current.isDefined) {
        mainToken = current
      }
    }
    // After loop keep last space token in case of TokenSep
    if (strMatch.isDefined) {
      current = Some(new TokenSep(strMatch.get, previousAux))
    }

    // After loop keep last other token in case of TokenOther
    if (strOther.isDefined) {
      current = Some(new TokenUnTyped(strOther.get, previousAux))
    }

    return mainToken
  }
}
