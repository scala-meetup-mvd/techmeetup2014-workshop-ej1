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

  def lines: Seq[String] = Source.fromFile(fileName).getLines.toSeq

  val allSongIds = allSongs.map {
      case songLineRegEx(_, songId, _*) => songId
  }

  def allSongs: Seq[String] = lines.filter {
      case songLineRegEx(_*) => true
      case _ => false
    }

  def uniqueSongs: Seq[String] = allSongIds
    .groupBy(s => s)
    .mapValues(_.size)
    .filter {case (song, count) => count == 1}
    .keys
    .toSeq

  def duplicateSongs: Seq[String] = allSongIds
    .groupBy(s => s)
    .mapValues(_.size)
    .filter {case (song, count) => count > 1}
    .flatMap {case (song, count) => List.fill(count)(song) }   
    .toSeq
}
