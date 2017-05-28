package org.mystic

import org.mystic.PaintShopApp._
import org.scalatest.{FlatSpec, Matchers}

class ComplexTest extends FlatSpec with Matchers {

  "A PaintShopSolver in test where number of paints less than customers" should "return result" in {
    val response = PaintShopSolver.solveAssignmentProblem(1, 2, Array(
      Array(1, 0),
      Array(1, 0)
    ))
    response.get should equal(Array(1))
    response.get should have length (1)
  }

  "A PaintShopSolver in test where number of paints less than customers complex" should "return None" in {
    val response = PaintShopSolver.solveAssignmentProblem(2, 4, Array(
      Array(1, getInfValue, 0, 0),
      Array(getInfValue, 1, 0, 0),
      Array(1, getInfValue, 0, 0),
      Array(getInfValue, 1, 0, 0)
    ))
    response.get should equal(Array(1, 1))
    response.get should have length (2)
  }

}
