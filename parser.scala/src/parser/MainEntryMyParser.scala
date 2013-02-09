package parser

import scala.io.Source;
import scala.util.matching.Regex

object MyParser extends App {
  println("Parser ....");
  val fileStr = "files/LKKBLU07.txt"
  // Comment trouver l'encoding de maniere automatique ( ici cp1252)

  private val reader = Source.fromFile(fileStr, "cp1252")
  private val natualPaser = new NaturalParsing()
  private val lexer = new Lexerizer(reader, natualPaser);
  
  private val mainToken: Option[Token] = lexer.run()
  
  

  private var current: Option[Token] =mainToken
  
  while (current.get.hasNext()) {
      println("<<" + current.get.value + ">> : "  + current.get.tokenType)
      current = current.get.next;
    }

};

