import java.awt.Image
import javax.swing.ImageIcon
case class Place() extends Node{
  override val canPass: Boolean = true
  override val img: Image = new ImageIcon("wall.jpg").getImage
}