package org.mystic

import java.io.{BufferedOutputStream, InputStreamReader, PrintWriter, Writer}
import java.util.Scanner

import com.typesafe.scalalogging.LazyLogging

/**
  * Main class, that reads/writes from/to input/output and solves the paint shop problem
  * For first test cases, where number of test cases (C) = 100, max(N, M) = 100, the hungarian algorithms will solve
  * the  problem pretty efficient with complexity <p>C * (max(N, M))&#94;3</p>, which is about 100&#94;4 operations
  *
  * However, for C = 5 and max(N, M) = 2000, the hungarian algorithm approach will be not that good (around 5 *
  * 2000&#94;3 operations).
  **/
object PaintShopApp extends App with LazyLogging {

  def getInfValue: Int = 10000

  /**
    * To do input/output to different sources (e.g file - change this code accordingly)
    *
    */
  val in = new Scanner(new InputStreamReader(System.in))
  val out = new PrintWriter(new BufferedOutputStream(System.out))
  solve
  out.close

  private def solve = {
    val numberOfTestCases = in.nextInt
    (1 to numberOfTestCases).foreach(solveTestCase(_, in, out))
  }

  /**
    * Solving 1 test case, reading the input and converting this format to the square matrix suitable for assignment problem solving
    *
    * @param testCaseNumber number of the test case, that we use for output printing
    */
  def solveTestCase(testCaseNumber: Int, in: Scanner, out: Writer): Unit = {
    // reading input data
    val numberOfPaints = in.nextInt
    val numberOfCustomers = in.nextInt
    val size = Math.max(numberOfPaints, numberOfCustomers)
    val arr = new Array[Array[Int]](size)
    for (i <- 0 until size)
      arr(i) = Array.fill[Int](numberOfPaints)(getInfValue)
    for (i <- 0 until numberOfCustomers) {
      val t = in.nextInt
      for (_ <- 0 until t) {
        val pos = in.nextInt - 1
        val value = in.nextInt
        arr(i)(pos) = value
      }
    }
    // make the matrix square
    if (numberOfPaints > numberOfCustomers) {
      for (i <- Math.min(numberOfPaints, numberOfCustomers) until size) {
        for (j <- 0 until size)
          arr(i)(j) = 0
      }
    }
    debugPrint(arr)
    out.write(s"Case #$testCaseNumber: ")
    HungarianAlgoSolver.solveAssignmentProblem(numberOfPaints, numberOfCustomers, arr) match {
      case Some(x) => {
        x.foreach(item => out.write(s"$item "))
        out.write("\n")
      }
      case None => {
        out.write("IMPOSSIBLE\n")
      }
    }
  }

  /**
    * Method that prints out values of the square matrix
    *
    * @param matrix square matrix
    */
  def debugPrint(matrix: Array[Array[Int]]): Unit = {
    val size = matrix.length
    val response = new StringBuilder("\n")
    for (i <- 0 until size) {
      for (j <- 0 until matrix(i).length)
        response.append(s"${matrix(i)(j)} ")
      response.append("\n")
    }
    logger.debug(response.toString())
  }

}
