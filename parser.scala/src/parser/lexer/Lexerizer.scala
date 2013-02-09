package parser

import scala.io.Source
import scala.util.matching.Regex
import scala.io.BufferedSource
import util.control.Breaks._

/**
 * Cette classe produit des tokens qui sont définit dans l'AbstractParser aPaser.
 *
 * @author ohaegi
 *
 */
class Lexerizer(val aReader: Iterator[Char], val aParser: AbstractParsing) {

  /**
   * Run the lexer - Main entry
   */
  def run(): Option[Token] = {
    var mainToken: Option[Token] = None
    if (aParser.listTokensParserDef().isEmpty) {
      mainToken = None
    } else {
      val firstDefLexer = aParser.listTokensParserDef().first
      mainToken = firstDefLexer.tokenize(aReader, None)
      run(mainToken)
    }
    mainToken
  }

  private def run(token: Option[Token]) {
    token match {
      case t: Option[TokenUnTyped] => runUnTyped(t)
      case t: Option[TokenTyped] => run(t.get.next)
      case _ => // End of lexing
    }
  }

  private def runUnTyped(token: Option[TokenUnTyped]) {
    aParser.listTokensParserDef().foreach {
      aDefLexer =>
        {
          val lexerdToken = aDefLexer.tokenize(token)
          if (lexerdToken.isDefined) {
            run(lexerdToken)
            break
          }
        }
    }
    // Write TokenNotTyped and attach new next of previous token
    val aTokenNotTyped = new TokenNotTyped(token.get.value, token.get.previous)
    if (token.get.previous.isDefined) {
      token.get.previous.get.next = Some(aTokenNotTyped)
    }
  }
}
