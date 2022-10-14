import java.awt.{Dimension, Toolkit}
import javax.swing.{JFrame, WindowConstants}

object Main extends App{
  val game = new Game(new GameInfo("test.txt"))
  val frame = new JFrame("Моя программа")
  frame.add(game)
  val info: Dimension = Toolkit.getDefaultToolkit.getScreenSize
  frame.setSize(info.width, info.height)
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  frame.setVisible(true)
}
