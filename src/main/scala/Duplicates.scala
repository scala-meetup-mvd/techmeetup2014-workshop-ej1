/** Implementación de búsqueda de duplicados.
  *
  * Se construye alrededor de un nombre de archivo.
  *
  * FIXME add implementations
  *
  */

import scala.io.Source

class Duplicates(val fileName: String) {

  private val impl = new DuplicatesRegex2(fileName)


  def lines: Seq[String] = impl.lines

  def allSongs: Seq[Long] = impl.allSongs

  def distinctSongs: Seq[Long] = impl.distinctSongs

  def duplicateSongs: Seq[Long] = impl.duplicateSongs

  def duplicateSongsWithCount: Map[Long,Int] = impl.duplicateSongsWithCount

}

class DuplicatesRegex2(val fileName: String) {

  val SongRegex = """\d+\s+(\d+)\s+\d+\s+[^\s].*""".r

  def lines: Seq[String] = Source.fromFile(fileName).getLines.toSeq

  def allSongs: Seq[Long] = lines.collect { case SongRegex(songIdStr) => songIdStr.toLong }

  def distinctSongs: Seq[Long] = allSongs.distinct

  def duplicateSongsWithCount: Map[Long, Int] = 
    allSongs
    .groupBy(identity)
    .filter(_._2.size >= 2)
    .mapValues(_.size) 

  def duplicateSongs: Seq[Long] = duplicateSongsWithCount.keys.toSeq

}


class DuplicatesSplit(val fileName: String) {

  import scala.util.Try

  def lines: Seq[String] = Source.fromFile(fileName).getLines.toSeq

  def linesWithId: Seq[(Long, String)] = { 
    val optLines = 
      for {
        line <- lines
      } yield {
        val cells = line.split("\\s+").lift
        for {
          _ <- cells(0)
          songIdStr <- cells(1)
          _ <- cells(2)
          _ <- cells(3)
          songId <- Try(songIdStr.toLong).toOption
        } yield {
          songId -> line
        }
      }

    optLines.flatten
  }

  def allSongs: Seq[String] = linesWithId.unzip._2

  def distinctSongs: Seq[String] = {
    val distinctSongs = linesWithId.groupBy(_._1).toSeq
    
    val res =
      for {
        (songId, lines) <- distinctSongs
        (_, repLine) = lines.head
      } yield {
        repLine
      }

    res
  }

  def duplicateSongs: Seq[String] = {
    val dupSongLines = linesWithId.groupBy(_._1).filter(_._2.size >= 2).toSeq
    
    val res =
      for {
        (songId, lines) <- dupSongLines
        (_, repLine) = lines.head
      } yield {
        repLine
      }

    res
  }

}

class DuplicatesRegex(val fileName: String) {

  val SongRegex = """\d+\s+(\d+)\s+\d+\s+[^\s].*""".r

  def lines: Seq[String] = Source.fromFile(fileName).getLines.toSeq

  def linesWithId: Seq[(Long, String)] = {
    lines.collect { 
        case line @ SongRegex(songId) => songId.toLong -> line
      }
  }

  def allSongs: Seq[String] = linesWithId.unzip._2

  def distinctSongs: Seq[String] = {
    val distinctSongs = linesWithId.groupBy(_._1).toSeq
    
    val res =
      for {
        (songId, lines) <- distinctSongs
        (_, repLine) = lines.head
      } yield {
        repLine
      }

    res
  }

  def duplicateSongs: Seq[String] = {
    val dupSongLines = linesWithId.groupBy(_._1).filter(_._2.size >= 2).toSeq
    
    val res =
      for {
        (songId, lines) <- dupSongLines
        (_, repLine) = lines.head
      } yield {
        repLine
      }

    res
  }

}
