package org.mystic

import org.scalatest.{FlatSpec, Matchers}

class SimpleTest extends FlatSpec with Matchers {

  "A HungarianAlgoSolver" should "return None" in {
    val response = HungarianAlgoSolver.solveAssignmentProblem(2, 2, Array(
      Array(1, PaintShopApp.getInfValue),
      Array(0, PaintShopApp.getInfValue)
    ))
    response should be(None)
  }

  "A HungarianAlgoSolver" should "return result" in {
    val response = HungarianAlgoSolver.solveAssignmentProblem(1, 1, Array(
      Array(1)
    ))
    response.get should have length (1)
    response.get should contain(1)
  }

}
