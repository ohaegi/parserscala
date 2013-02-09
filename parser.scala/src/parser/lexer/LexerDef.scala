package parser
import scala.util.matching.Regex

// Define token type parser
abstract class LexerDef(tokenType: String) {
  def tokenize(value: String, previous: Option[Token]): Option[Token] = None
  def tokenize(value: Iterator[Char], previous: Option[Token]): Option[Token] = None
  def tokenize(value: Option[TokenUnTyped]): Option[Token] = None
}

