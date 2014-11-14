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

  def allSongs: Seq[Long] = lines.filter {
      case songLineRegEx(_*) => true
      case _ => false
    }.map {
      case songLineRegEx(_, songId, _*) => songId.toLong
    }

  def distinctSongs: Seq[Long] = allSongs
    .groupBy(s => s)
    .keys
    .toSeq

  def duplicateSongs: Seq[Long] = allSongs
    .groupBy(s => s)
    .mapValues(_.size)
    .filter {case (song, count) => count > 1}
    .keys
    .toSeq

  def duplicateSongsWithCount: Map[Long,Int] = allSongs
    .groupBy(s => s)
    .mapValues(_.size)
    .filter {case (song, count) => count > 1}

}
