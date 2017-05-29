package org.mystic

import org.mystic.PaintShopApp._
import org.scalatest.{FlatSpec, Matchers}

class ComplexTest extends FlatSpec with Matchers {

  "A HungarianAlgoSolver in test where number of paints less than customers" should "return result" in {
    val response = HungarianAlgoSolver.solveAssignmentProblem(1, 2, Array(
      Array(1, 0),
      Array(1, 0)
    ))
    response.get should equal(Array(1))
    response.get should have length (1)
  }

  "A HungarianAlgoSolver in test where number of paints less than customers complex" should "return result" in {
    val response = HungarianAlgoSolver.solveAssignmentProblem(2, 4, Array(
      Array(1, getInfValue, 0, 0),
      Array(getInfValue, 1, 0, 0),
      Array(1, getInfValue, 0, 0),
      Array(getInfValue, 1, 0, 0)
    ))
    response.get should equal(Array(1, 1))
    response.get should have length (2)
  }

  "A HungarianAlgoSolver with optimal solution of 0s" should "return res" in {
    val response = HungarianAlgoSolver.solveAssignmentProblem(4, 4, Array(
      Array(1, 0, getInfValue, getInfValue),
      Array(0, 1, getInfValue, getInfValue),
      Array(getInfValue, 0, 1, getInfValue),
      Array(getInfValue, 1, 0, 0)
    ))
    response.get should equal(Array(0, 0, 0, 0))
    response.get should have length (4)
  }

}
