import java.awt.Image
import javax.swing.ImageIcon

case class Wall() extends Node{
  override val canPass: Boolean = false
  val img: Image = new ImageIcon("wall.jpg").getImage
}