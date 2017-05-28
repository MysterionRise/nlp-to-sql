package org.mystic

import java.io.{BufferedOutputStream, InputStreamReader, PrintWriter}
import java.util.Scanner

import com.typesafe.scalalogging.LazyLogging

/**
  * Main class, that reads/writes from/to input/output and solves the paint shop problem
  */
object PaintShopApp extends App with LazyLogging {

  def getInfValue: Int = Int.MaxValue / 2

  /**
    * To do input/output to different sources (e.g file - change this code accordingly)
    *
    */
  val in = new Scanner(new InputStreamReader(System.in))
  val out = new PrintWriter(new BufferedOutputStream(System.out))
  solve
  out.close

  def nextInt: Int = in.nextInt()

  private def solve = {
    val numberOfTestCases = nextInt
    (1 to nextInt).foreach(solveTestCase(_))
  }

  /**
    * Solving 1 test case, reading the input and converting this format to the square matrix suitable for assignment problem solving
    *
    * @param testCaseNumber number of the test case, that we use for output printing
    */
  def solveTestCase(testCaseNumber: Int): Unit = {
    val numberOfPaints = nextInt
    val numberOfCustomers = nextInt
    val size = Math.max(numberOfPaints, numberOfCustomers)
    val arr = new Array[Array[Int]](size)
    for (i <- 0 until size)
      arr(i) = Array.fill[Int](size)(getInfValue)
    for (i <- 0 until numberOfCustomers) {
      val t = nextInt
      for (_ <- 0 until t) {
        val pos = nextInt - 1
        val value = nextInt
        arr(i)(pos) = value
      }
    }
    if (numberOfPaints > numberOfCustomers) {
      for (i <- Math.min(numberOfPaints, numberOfCustomers) until size) {
        for (j <- 0 until size)
          arr(i)(j) = 0
      }
    }
    debugPrint(arr)
    out.print(s"Case #$testCaseNumber: ")
    PaintShopSolver.solveAssignmentProblem(numberOfPaints, numberOfCustomers, arr) match {
      case Some(x) => {
        x.foreach(item => out.print(s"$item "))
        out.println
      }
      case None => {
        out.println("IMPOSSIBLE")
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
      for (j <- 0 until size)
        response.append(s"${matrix(i)(j)} ")
      response.append("\n")
    }
    logger.debug(response.toString())
  }

}
