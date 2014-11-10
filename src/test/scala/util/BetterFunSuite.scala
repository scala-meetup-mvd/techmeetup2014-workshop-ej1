package util

import org.scalatest._

/** Base class for tests */
trait BetterFunSuite extends FlatSpec
  with Matchers
  with OptionValues
  with Inside
  with Inspectors {

    /** Using `pending` directly triggers discard-value warning, use this instead */
    def pendingU(): Unit = {
      val x = pending
    }

    def pendingUntilFixedU(f: => Unit): Unit = {
      val x = pendingUntilFixed(f)
    }

}


