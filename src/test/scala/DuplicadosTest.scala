
import org.scalatest.FunSuite

class DuplicadosTest extends FunSuite  {

  def historySongDuplicates: Map[Long, Int] = {
    val url = getClass.getResource("HistorySong.txt")
    TextProcessing.findDuplicates(url)
  }

  test("can handle tab separated record") {
    val dup = historySongDuplicates
    assert(dup.contains(216869))
  }

  test("can handle space instead of tab between numbers") {
    val dup = historySongDuplicates
    assert(dup.contains(257560))
  }

  test("can handle tab inside ArtistName") {
    val dup = historySongDuplicates
    assert(dup.contains(188053))
  }

  test("finds all duplicates") {
    val dup = historySongDuplicates
    assert(dup.size === 6)
  }
 
}

