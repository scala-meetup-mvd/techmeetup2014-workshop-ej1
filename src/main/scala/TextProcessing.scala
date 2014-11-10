import scala.io.Source
import java.net.URL

object TextProcessing {

  def findDuplicates(url: URL): Map[Long, Int] = {
    findDuplicatesGerman(url)
  }

  def findDuplicatesGerman(url: URL): Map[Long, Int] = {
    println("Working with: " + url)
    val src = Source.fromURL(url)

    case class Record(num: Int, songId: Long, seedId: Long, rest: String)
    case class FullRecord(num: Int, songId: Long, seedId: Long, songName: String, artistName: String, artistSeed: Long)

    // A valid line is anything starting with three numbers and finishing with something different than a whitespace.
    val RecordRegex     = """(\d+)\s+(\d+)\s+(\d+)\s+([^\s].*)""".r
    val FullRecordRegex = """(\d+)\s+(\d+)\s+(\d+)\s+([^\s].*[^\s])\s*\t\s*([^\s].*[^\s])\s*\t\s*(\d+)""".r

    // Iterator over valid lines, with relevant data parsed
    val songRecords = src.getLines.collect {
      case RecordRegex(numStr, songIdStr, seedIdStr, rest) => /*println("lazy?");*/ Record(numStr.toInt, songIdStr.toLong, seedIdStr.toLong, rest)
      //case FullRecordRegex(numStr, songIdStr, seedIdStr, songName, artistName, artistSeedStr) => FullRecord(numStr.toInt, songIdStr.toLong, seedIdStr.toLong, songName, artistName, artistSeedStr.toLong)
    }        

    // Convert to Iterable to iterate over the lines more than once (for debug primarily).
    val songRecordsIt = songRecords.toIterable
    //println(s"Lines ${songRecordsIt.size}")
    //println(songRecordsIt.mkString("\n"))

    // Group lines by `songId`
    val bySongId = songRecordsIt.groupBy(_.songId)

    // Repeated songs will have more than one value associated to the songId
    val repeatedSongs = bySongId.filter(_._2.size >= 2) 

    val report = 
      for {
        (songId, records) <- repeatedSongs
        count = records.size
        song = records.head
      } yield {
        song -> count
      }

    println("Duplicates")
    println(report.mkString("\n"))
    println("FIN")

    report.map{ case (k, v) => k.songId -> v }.toMap
  }
}