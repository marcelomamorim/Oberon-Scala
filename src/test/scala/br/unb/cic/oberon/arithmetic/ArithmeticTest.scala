package br.unb.cic.oberon.arithmetic

import br.unb.cic.oberon.ir.ast.{IntValue, RealValue, MultExpression}
import org.scalatest.funsuite.AnyFunSuite
import br.unb.cic.oberon.interpreter.{Interpreter, EvalExpressionVisitor}

class ArithmeticTestSuite extends AnyFunSuite {
  test("Test sum between two integers") {
    val i05 = IntValue(5)
    val i10 = IntValue(10)
    val expected = IntValue(15)

    assert(expected == (i05 + i10))
  }

  test("Test a sum between one integer and one real") {
    val i05 = IntValue(5)
    val i10 = RealValue(10)
    val expected = RealValue(15)

    assert(expected == (i05 + i10))
  }

  test("Test a division between two integers") {
    val i02 = IntValue(2)
    val i06 = IntValue(6)
    val i09 = IntValue(9)

    var expected = IntValue(3)

    assert(expected == (i06 / i02))

    expected = IntValue(4)

    assert(expected == (i09 / i02))
  }

  test("Test a multiplication between two real"){

    val r10 = RealValue(10.0)
    val r2 = RealValue(2.0)
    val m = MultExpression(r10, r2)

    val expected = RealValue(20.0)

    val interpreter = new Interpreter()
    val expVisitor = new EvalExpressionVisitor(interpreter)

    val res = m.accept(expVisitor)

    assert(expected == res)

  }

  test("Test a division by zero") {

    val i0 = IntValue(0)
    val i06 = IntValue(6)

    assertThrows[ArithmeticException] {
      i06./(i0)
    }

  }

}
