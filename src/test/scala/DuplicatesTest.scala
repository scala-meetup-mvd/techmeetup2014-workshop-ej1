
import util.BetterFunSuite


class DuplicatesTest extends BetterFunSuite  {

  val relativeName = "src/test/resources/HistorySong.txt"

  val allLinesCount     = 129
  val allSongsCount     = 100
  val uniqueSongsCount  = 94
  val duplicateSongsIdCountMap = Map("137802" -> 2, "216869" -> 2, "190961" -> 2, "130280" -> 2, "113955" -> 2, "257560" -> 2)

  "Duplicados" should "be able to read all lines" in {
    val dupes = new Duplicates(relativeName)
    assert (dupes.lines.size === allLinesCount)
  }

  it should "be able to list song ids" in {
    val dupes = new Duplicates(relativeName)
    assert ( dupes.allSongs.size === allSongsCount )
  }

  it should "be able to list all distinct song ids" in {
    val dupes = new Duplicates(relativeName)
    assert ( dupes.distinctSongs.size === uniqueSongsCount )
  }

  it should "be able to list the duplicates" in {
    val dupes = new Duplicates(relativeName)
    assert ( dupes.duplicateSongs.size === ( allSongsCount - dupes.distinctSongs.size ) )
  }

  it should "find the correct duplicate song ids" in {
    val dupes = new Duplicates(relativeName)
    duplicateSongsIdCountMap.keys.foreach { 
      case song => assert (dupes.duplicateSongsWithCount.keys.exists(_ == song) )
    }
  }

  it should "be able to emit the correct count for each duplicated song" in {
    val dupes = new Duplicates(relativeName)
    duplicateSongsIdCountMap.foreach { 
      case (song, count) => assert (dupes.duplicateSongsWithCount.exists(_ == (song, count)) )
    }
  }
  
}
