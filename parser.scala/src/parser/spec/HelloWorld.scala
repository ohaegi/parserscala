package parser.spec


import org.junit.runner.RunWith
import org.specs.matcher._
import org.specs.runner.{ JUnitSuiteRunner, JUnit }
import org.specs.Specification

@RunWith(classOf[JUnitSuiteRunner])
class MyTestSpecTest extends Specification with JUnit {
  "hello world" should {
    "have 11 characters" in {
      "hello world".size must_== 11
    }
  }
};
