import java.awt.Image
import javax.swing.ImageIcon

case class Enemy() extends Human {
  override val img: Image = new ImageIcon("player1.png").getImage
}