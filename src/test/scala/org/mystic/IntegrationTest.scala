package org.mystic

import java.io.{FileInputStream, InputStreamReader, StringReader, StringWriter}
import java.util.Scanner

import org.scalatest.{FlatSpec, Matchers}

class IntegrationTest extends FlatSpec with Matchers {

  "PaintShop App" should "read and return expected format of the reponse" in {
    val in = new Scanner(new InputStreamReader(new FileInputStream(getClass.getResource("/input.txt").getPath)))
    val out = new StringWriter()
    val numberOfTestCases = in.nextInt()
    numberOfTestCases should equal (5)
    for (i <- 1 to 5) {
      PaintShopApp.solveTestCase(i, in, out)
    }
    println(out.toString)
    out.toString should equal ("Case #1: 1 0 0 0 0 \nCase #2: IMPOSSIBLE\nCase #3: 1 \nCase #4: 1 1 \nCase #5: 0 0 0 0 \n")
  }

}
