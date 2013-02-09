package parser
import scala.util.matching.Regex

class Token(val value: String, val previous: Option[Token], val tokenType:String) {
  var next: Option[Token] = None;
  // Init next of previous token
  {
    if (previous.isDefined) {
      previous.get.next = Some(this);
    }
  }

  def hasNext(): Boolean = next.isDefined
}

class TokenSep(v: String, previous: Option[Token]) extends Token(v, previous,"Sep");
class TokenUnTyped(v: String, previous: Option[Token]) extends Token(v, previous,"unTyped");
class TokenNotTyped(v: String, previous: Option[Token]) extends Token(v, previous,"unTyped");
class TokenTyped(v: String, previous: Option[Token], tokenType : String) extends Token(v, previous, tokenType);
