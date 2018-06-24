package com.sksamuel.scapegoat.inspections.unnecessary

import com.sksamuel.scapegoat.PluginRunner
import com.sksamuel.scapegoat.inspections.unneccesary.UnnecessaryReturnUse
import org.scalatest.{ FreeSpec, Matchers }

/** @author Stephen Samuel */
class UnnecessaryReturnUseTest extends FreeSpec with Matchers with PluginRunner {

  override val inspections = Seq(new UnnecessaryReturnUse)

  "return keyword use" - {
    "should report warning" in {

      val code = """class Test {
                      def hello : String = {
                        val s = "sammy"
                        return s
                      }

                      def earlyOutReturn: Unit = {
                        if(Math.random() > 0.5) {
                          println("early out return")
                          return () // Acceptable
                        }
                        println("not reachable")
                      }
                    } """.stripMargin

      compileCodeSnippet(code)
      compiler.scapegoat.feedback.warnings.size shouldBe 1
    }
  }
}
