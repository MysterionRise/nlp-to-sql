package org.mystic

import org.mystic.PaintShopApp.getInfValue
import org.scalatest.{FlatSpec, Matchers}

class SimpleTest extends FlatSpec with Matchers {

  "A HungarianAlgoSolver" should "return None" in {
    val response = HungarianAlgoSolver.solveAssignmentProblem(2, 2, Array(
      Array(1, getInfValue),
      Array(0, getInfValue)
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
