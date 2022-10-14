import java.awt.Image

abstract class Node {
  val canPass: Boolean = false
  var human: Option[Human] = None
  val img: Image
}
