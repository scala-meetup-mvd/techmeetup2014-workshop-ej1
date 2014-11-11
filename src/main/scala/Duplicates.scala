/** Implementación de búsqueda de duplicados.
  *
  * Se construye alrededor de un nombre de archivo.
  *
  * FIXME add implementations
  *
  */
import scala.io.Source

class Duplicates(val fileName: String) {

  val songLineRegEx = """(\d+)\s+(\d+)\s+(\d+)(.*)""".r

  val allSongIds = allSongs.map {
      case songLineRegEx(_, songId, _*) => songId.toLong
  }

  def lines: Seq[String] = Source.fromFile(fileName).getLines.toSeq

  def allSongs: Seq[String] = lines.filter {
      case songLineRegEx(_*) => true
      case _ => false
    }

  def distinctSongs: Seq[Long] = allSongIds
    .groupBy(s => s)
    .keys
    .toSeq

  def duplicateSongs: Seq[Long] = allSongIds
    .groupBy(s => s)
    .mapValues(_.size)
    .filter {case (song, count) => count > 1}
    .keys
    .toSeq

  def duplicateSongsWithCount: Map[Long,Int] = allSongIds
    .groupBy(s => s)
    .mapValues(_.size)
    .filter {case (song, count) => count > 1}

}
