package br.unb.cic.oberon.ir.tac

import br.unb.cic.oberon.ir.ast._
import org.scalatest.funsuite.AnyFunSuite

class InstructionTestSuite extends AnyFunSuite {
  test("Test temporary counter") {
    Temporary.reset
    val t0 = new Temporary()
    val t1 = new Temporary()
    
    assert(t0.number == 0 && t1.number == 1)
  }
}
