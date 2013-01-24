package parser

import scala.io.Source
import scala.util.matching.Regex
import scala.io.BufferedSource

/**
 * Cette classe produit de token d'espace ou d'autres types.
 *
 * @author ohaegi
 *
 */
class TokenizerSep(val aReader: BufferedSource, val aParser: AbstractParsing) {
  private var mainToken: Option[Token] = None
  private var previous: Option[Token] = None
  private var current: Option[Token] = None

  def getMainToken(): Option[Token] = {
    if (!mainToken.isDefined) {
      parse
    }
    mainToken
  }

  def parse() {
    var strSep: Option[String] = None
    var strOther: Option[String] = None
    while (aReader.hasNext) {
      val c = aReader.next()
      aParser.charSepParser(c) match {
        case CharParserSep(c) =>
          
          strSep = Some(strSep.getOrElse("") + c.toString())
          if (strOther.isDefined) {
            current = Some(new TokenUnknow(strOther.get, previous))
            strOther = None
            previous = current
          }
        case _ =>
          strOther = Some(strOther.getOrElse("") + c.toString())
          if (strSep.isDefined) {
            current = Some(new TokenSep(strSep.get, previous))
            strSep = None
            previous = current
          }
      }
      // Keep the main token
      if (mainToken.isEmpty && current.isDefined) {
        mainToken = current
      }
    }
  	// After loop keep last space token in case of TokenSep
    if (strSep.isDefined) {
      current = Some(new TokenSep(strSep.get, previous))
    }
    
    // After loop keep last other token in case of TokenOther
    if (strOther.isDefined) {
      current = Some(new TokenUnknow(strOther.get, previous))
    }
  }
}

