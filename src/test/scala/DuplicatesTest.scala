
import util.BetterFunSuite


class DuplicatesTest extends BetterFunSuite  {

  val relativeName = "src/test/resources/HistorySong.txt"

  val allSongsCount     = 100
  val uniqueSongsCount  = 94

  "Duplicados" should "be able to read all lines" in {
    val size = 130
    val dupes = new Duplicates(relativeName)
    assert (dupes.lines.size == size)
  }

  it should "be able to list song ids" in {
    val dupes = new Duplicates(relativeName)
    assert ( dupes.allSongs.size == allSongsCount )
  }

  it should "be able to list all unique song ids" in {
    val dupes = new Duplicates(relativeName)
    assert ( dupes.uniqueSongs.size == uniqueSongsCount )
  }

  it should "be able to list the duplicates" in {
    val dupes = new Duplicates(relativeName)
    assert ( dupes.duplicateSongs.size == (dupes.uniqueSongs.size - uniqueSongsCount) )
  }

  it should "be able to emit the correct count for each duplicated song" in pendingU()

  // FIXME ADD tests that test for specific songids. check list equality.
  it should "find the correct duplicate song ids" in pendingU()




}

