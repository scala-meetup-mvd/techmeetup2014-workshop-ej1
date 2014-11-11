import java.nio.file.{Files, Paths}

import collection.JavaConversions._

/** Implementación de búsqueda de duplicados.
  *
  * Se construye alrededor de un nombre de archivo.
  *
  * FIXME add implementations
  *
  */
class Duplicates(val fileName: String) {

  val path = Paths.get(fileName)

  val R = """^([0-9]*)[ \t]([0-9]*)[ \t]([0-9]*).*""".r

  def lines: Seq[String] = Files.readAllLines(path).toVector

  def allSongs: Seq[String] = lines collect { case R(a,b,c) => b }

  def distinctSongs: Seq[String] = allSongs.toSet.toSeq

  def songsWithCount: Map[String, Int] =
    allSongs
      .groupBy(identity)
      .map{ case (k,v) => (k,v.size)}

  def duplicateSongs: Seq[String] =
    songsWithCount
      .collect { case (k,v) if v > 1 => k }
      .toSeq

}
