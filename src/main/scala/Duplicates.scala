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
