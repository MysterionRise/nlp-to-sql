package org.mystic

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object HungarianAlgoSolver {

  /**
    * Testing current matrix that it could be covered with N number of zero crossed columns
    *
    * @param matrix
    * @return None, if it's not possible and Some(res) if it's possible
    */
  def testMinimalCoverageByColumns(matrix: Array[Array[Int]]): Option[Array[Int]] = {
    val solution = Array.fill(matrix.length)(0)
    testSolution(matrix, solution) match {
      case true => Some(solution)
      case _ => None
    }
  }

  def testSolution(matrix: Array[Array[Int]], sol: Array[Int]): Boolean = {
    val size = matrix.length
    val set = new mutable.HashSet[Int]()
    for (i <- 0 until size)
      for (j <- 0 until size) {
        if (matrix(i)(j) == sol(j)) {
          set.add(i)
        }
      }
    // if set is complete than this solution is okay
    set.size == size
  }

  def removeSmallestElementFromEachRow(matrix: Array[Array[Int]]): Unit = {
    for (i <- 0 until matrix.length) {
      val min = matrix(i).min
      for (j <- 0 until matrix.length)
        matrix(i)(j) -= min
    }
  }

  def getSolution(matrix: Array[Array[Int]]) = {
    val size = matrix.length
    val rowZeros = new Array[Int](size)
    val colZeros = new Array[Int](size)
    val transpose = matrix.transpose
    for (i <- 0 until size) {
      rowZeros(i) = matrix(i).count(_ == 0)
      colZeros(i) = transpose.apply(i).count(_ == 0)
    }
    val response = new Array()
    val min = Math.min(rowZeros.min, colZeros.min)
    val pos = (rowZeros ++ colZeros).indexOf(min)
    var flag = true
    while (flag) {

    }


    /**
      * Calculate the number of 0 elements for each row and column. (call it row[] and column[])
      * Select the minimum positive value of the rows and columns.
      * For example let it be column[3] (if the minimum value is found in a row, the same applies, only swap rows and columns)
      * If you have more than one with the same value, select any.
      * Select a 0 element in that column, mark it. If you have more than one, select any.
      * Set column[3] to 0 (next time don't select)
      * Iterate in all elements in column[3], if you find a 0 element, decrease the corresponding row[i] value by 1
      * If you don't find a positive value in neither row or column, you are finished.
      */
  }

  /**
    * Solves the assignment problem with O(N&#94;3) complexity, where N = dimension of the matrix
    *
    * @param matrix
    * @return the tuple with first element being cost and second - the array of results, where
    */
  def solve(matrix: Array[Array[Int]]): (Int, Array[Int]) = {
    //    testMinimalCoverageByColumns(matrix) match {
    //      case None => {
    //        (0, null)
    //      }
    //      case Some(sol) =>
    //        (sol.sum, sol)
    //    }
    val solution = Array.fill(matrix.length)(0)
    while (!testSolution(matrix, solution)) {
      removeSmallestElementFromEachRow(matrix)
    }
    val finalSolution = getSolution(matrix)
    PaintShopApp.debugPrint(matrix)
    (solution.sum, solution)
    //    val size = matrix.length
    //    val u = new Array[Int](size)
    //    val v = new Array[Int](size)
    //    val markIndices = Array.fill(size)(-1)
    //
    //    for (i <- 0 until size) {
    //      val links = Array.fill(size)(-1)
    //      val mins = Array.fill(size)(PaintShopApp.getInfValue)
    //      val visited = new Array[Boolean](size)
    //      var markedI = i
    //      var markedJ = -1
    //      var j = 0
    //      do {
    //        j = -1
    //        for (j1 <- 0 until size) {
    //          if (!visited(j1)) {
    //            if (matrix(markedI)(j1) - u(markedI) - v(j1) < mins(j1)) {
    //              mins(j1) = matrix(markedI)(j1) - u(markedI) - v(j1)
    //              links(j1) = markedJ
    //            }
    //            if (j == -1 || mins(j1) < mins(j))
    //              j = j1
    //          }
    //        }
    //        val delta = mins(j)
    //        for (j1 <- 0 until size) {
    //          if (visited(j1)) {
    //            u(markIndices(j1)) += delta
    //            v(j1) -= delta
    //          } else {
    //            mins(j1) -= delta
    //          }
    //        }
    //        u(i) += delta
    //        visited(j) = true
    //        markedJ = j
    //        markedI = markIndices(j)
    //      } while (markedI != -1)
    //      while (links(j) != -1) {
    //        markIndices(j) = markIndices(links(j))
    //        j = links(j)
    //      }
    //      markIndices(j) = i
    //
    //    }
    //    extractTheSolution(matrix, size, markIndices)
  }

  private def extractTheSolution(matrix: Array[Array[Int]], size: Int, markIndices: Array[Int]) = {
    val result = new Array[Int](size)
    var cost = 0
    for (j <- 0 until size) {
      cost += matrix(markIndices(j))(j)
      result(markIndices(j)) = j
    }
    (cost, result)
  }

  /**
    *
    * @param numberOfPaints
    * @param numberOfCustomers
    * @param matrix
    * @return None if it's impossible to satisfy all customers or Some if it's possible
    */
  def solveAssignmentProblem(numberOfPaints: Int, numberOfCustomers: Int, matrix: Array[Array[Int]]): Option[Array[Int]] = {
    val result = solve(matrix)
    result._1 match {
      case cost if cost >= PaintShopApp.getInfValue => None
      case _ => Some(getResponse(result._2, matrix, numberOfPaints))
    }
  }

  def getResponse(res: Array[Int], arr: Array[Array[Int]], numberOfPaints: Int): Array[Int] = {
    val arrBuffer = new ArrayBuffer[Int]()
    (0 until res.length).foreach(i => arrBuffer.append(res(i)))
    //    (0 until res.length).foreach(i => arrBuffer.append(arr(i)(res(i))))
    arrBuffer.drop(arr.length - numberOfPaints).toArray
  }

}
