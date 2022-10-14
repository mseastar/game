import java.awt.{Color, Image}
import javax.swing.ImageIcon

class Player(var x: Int, var y: Int) extends Human {
  var lstColors: Vector[Color] = Vector(Color.WHITE, Color.RED, Color.GREEN)
  var godMode: Boolean = true
  override val img: Image = new ImageIcon("player1.png").getImage
  def createWall(map: Vector[Vector[Node]]) : Vector[Vector[Node]] = map.updated(y, map(y).updated(x, if (map(y)(x) == Place()) Wall() else Place()))

  def moveW() : Unit = y -= 1
  def moveS() : Unit = y += 1
  def moveD() : Unit = x += 1
  def moveA() : Unit = x -= 1
}
