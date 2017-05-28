package org.mystic

import scala.collection.mutable.ArrayBuffer

object PaintShopSolver {

  /**
    *
    * @param numberOfPaints
    * @param numberOfCustomers
    * @param matrix
    * @return None if it's impossible to satisfy all customers or Some if it's possible
    */
  def solveAssignmentProblem(numberOfPaints: Int, numberOfCustomers: Int, matrix: Array[Array[Int]]): Option[Array[Int]] = {
    val size = matrix.length
    val res = new Array[Int](size)
    val cost = HungarianAlgo.solve(matrix, res)
    cost match {
      case cost if cost >= PaintShopApp.getInfValue => None
      case _ => Some(getResponse(res, matrix, numberOfPaints))
    }
  }

  def getResponse(res: Array[Int], arr: Array[Array[Int]], numberOfPaints: Int): Array[Int] = {
    val arrBuffer = new ArrayBuffer[Int]()
    for (i <- 0 until res.length) {
      arrBuffer.append(arr(i)(res(i)))
    }
    arrBuffer.drop(arr.length - numberOfPaints).toArray
  }

}
