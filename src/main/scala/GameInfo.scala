import scala.io.{BufferedSource, Source}

class GameInfo(path: String) {
  val source: BufferedSource = Source.fromFile(path)
  val (row, col): (Int, Int) = source.getLines().take(2).toList match {case List(x, y) => (x.toInt, y.toInt)}
  val (xPlayer, yPlayer): (Int, Int) = source.getLines().take(2).toList match {case List(x, y) => (x.toInt, y.toInt)}
  val player = new Player(xPlayer, yPlayer)
  var map: Vector[Vector[Node]] = Vector.fill(row, col)(Place())
  source.close()
  for {i <- map.zipWithIndex
    j <- i._1.zipWithIndex
  } {
    if (j._2 == 0 || j._2 == col - 1 || i._2 == 0 || i._2 == row - 1)
      map = map.updated(i._2, map(i._2).updated(j._2, Wall()))
  }

}
