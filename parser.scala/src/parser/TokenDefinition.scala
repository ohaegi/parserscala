package parser

class Token(val value: String, val previous: Option[Token], val tokenType:String) {
  var next: Option[Token] = None;
  // Init next of previous token
  {
    if (previous.isDefined) {
      previous.get.next = Some(this);
    };
  };

  def hasNext(): Boolean = next.isDefined;
};

class TokenSep(v: String, previous: Option[Token]) extends Token(v, previous,"Sep");
class TokenUnknow(v: String, previous: Option[Token]) extends Token(v, previous,"unknow");
