package org.mystic

import org.mystic.PaintShopApp.getInfValue

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object HungarianAlgoSolver {

  /**
    * Testing current matrix that it could be covered with N number of zero crossed columns
    *
    * @param matrix
    * @return None, if it's not possible and Some(res) if it's possible
    */
  def testMinimalCoverageByColumns(matrix: Array[Array[Int]], numberOfPaints: Int): Option[Array[Int]] = {
    val solution = Array.fill(numberOfPaints)(0)
    testSolution(matrix, solution, numberOfPaints) match {
      case true => Some(solution)
      case _ => None
    }
  }

  def testSolution(matrix: Array[Array[Int]], sol: Array[Int], numberOfPaints: Int): Boolean = {
    val size = matrix.length
    val set = new mutable.HashSet[Int]()
    for (i <- 0 until size)
      for (j <- 0 until numberOfPaints) {
        if (matrix(i)(j) == sol(j)) {
          set.add(i)
        }
      }
    // if set is complete than this solution is okay
    set.size == size
  }

  def removeSmallestElementFromEachRow(matrix: Array[Array[Int]]): Boolean = {
    var flag = false
    for (i <- 0 until matrix.length) {
      val min = matrix(i).min
      if (min > 0) {
        flag = true
      }
      for (j <- 0 until matrix(i).length)
        matrix(i)(j) -= min
    }
    flag
  }

  def getSolution(arr: Array[Array[Int]], initialMatrix: Array[Array[Int]], numberOfPaints: Int): Array[Int] = {
    val matrix = arr.transpose.transpose
    val size = matrix.length
    val rowZeros = new Array[Int](size)
    val colZeros = new Array[Int](numberOfPaints)
    var transpose = matrix.transpose
    for (i <- 0 until size) {
      rowZeros(i) = matrix(i).count(_ == 0)
    }
    for (i <- 0 until numberOfPaints) {
      colZeros(i) = transpose(i).count(_ == 0)
    }
    val response = new ArrayBuffer[(Int, Int)]()
    var pos = (rowZeros ++ colZeros).indexOf(Math.min(rowZeros.min, colZeros.min))
    for (iter <- 0 until numberOfPaints) {
      transpose = matrix.transpose
      if (pos >= size) {
        colZeros(pos - size) = getInfValue
        val row = transpose(pos - size)
        val firstZero = row.indexOf(0)
        if (firstZero == -1) {
          return Array.fill(size)(getInfValue)
        }
        response.append((pos - size, firstZero))
        rowZeros(firstZero) = getInfValue
        colZeros(pos - size) = getInfValue
        for (j <- 0 until size) {
          matrix(pos - size)(j) = getInfValue
          matrix(j)(firstZero) = getInfValue
        }

      } else {
        rowZeros(pos) = getInfValue
        val row = matrix(pos)
        val firstZero = row.indexOf(0)
        if (firstZero == -1) {
          return Array.fill(size)(getInfValue)
        }
        response.append((pos, firstZero))
        rowZeros(firstZero) = getInfValue
        colZeros(pos) = getInfValue
        for (j <- 0 until size) {
          matrix(j)(pos) = getInfValue
        }
        for (j <- 0 until numberOfPaints) {
          matrix(firstZero)(j) = getInfValue
        }
      }
      pos = (rowZeros ++ colZeros).indexOf(Math.min(rowZeros.min, colZeros.min))
    }
    response.sortBy(_._2).map(x => initialMatrix(x._1)(x._2)).toArray
  }

  /**
    * Solves the assignment problem with O(N&#94;3) complexity, where N = dimension of the matrix
    *
    * @param arr
    * @return the tuple with first element being cost and second - the array of results, where
    */
  def solve(arr: Array[Array[Int]], numberOfPaints: Int): (Int, Array[Int]) = {
    val matrix = arr.transpose.transpose
    val solution = Array.fill(numberOfPaints)(0)
    var somethingIsDone = true
    while (!testSolution(matrix, solution, numberOfPaints) && somethingIsDone) {
      somethingIsDone = removeSmallestElementFromEachRow(matrix)
    }
    val finalSolution = getSolution(matrix, arr, numberOfPaints)
    if (testSolution(arr, finalSolution, numberOfPaints)) {
      PaintShopApp.debugPrint(matrix)
      (finalSolution.sum, finalSolution)
    } else {
      (getInfValue, Array())
    }
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
    val result = solve(matrix, numberOfPaints)
    result._1 match {
      case cost if cost >= getInfValue => None
      case _ => Some(result._2)
    }
  }

}
