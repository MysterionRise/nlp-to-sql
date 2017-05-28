package org.mystic

import org.mystic.PaintShopApp._
import org.scalatest.{FlatSpec, Matchers}

class BuiltInTest extends FlatSpec with Matchers {

  "A PaintShopSolver in test 1" should "return None" in {
    val response = PaintShopSolver.solveAssignmentProblem(5, 3, Array(
      Array(1, getInfValue, getInfValue, getInfValue, getInfValue),
      Array(0, 0, getInfValue, getInfValue, getInfValue),
      Array(getInfValue, getInfValue, getInfValue, getInfValue, 0),
      Array(0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0)
    ))
    response.get should equal(Array(1, 0, 0, 0, 0))
    response.get should have length (5)
  }

  "A PaintShopSolver in test 2" should "return None" in {
    val response = PaintShopSolver.solveAssignmentProblem(1, 2, Array(
      Array(0, getInfValue),
      Array(1, getInfValue)
    ))
    response should be (None)
  }

}