import java.awt.{Color, Dimension, Graphics, Toolkit}
import javax.swing.{JPanel, Timer}
import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}

class Game(game: GameInfo) extends JPanel with ActionListener{
  private val t: Timer = new Timer(1000 / 75, this)
  val info: Dimension = Toolkit.getDefaultToolkit.getScreenSize
  val xSize: Int = (info.width - 20) / game.col
  val ySize: Int = (info.height - 120) / game.row
  t.start()
  addKeyListener(new Keys)
  super.setFocusable(true)
  private class Keys extends KeyAdapter {
    override def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case 87 if game.player.y != 0 &&
          ((game.map(game.player.y - 1)(game.player.x) match {
            case Place() => true
            case _ => false}) ||
            game.player.godMode) => game.player.moveW()
        case 83 if game.player.y != game.row - 1 &&
          ((game.map(game.player.y + 1)(game.player.x) match {
            case Place() => true case _ => false}) ||
            game.player.godMode) => game.player.moveS()
        case 68 if game.player.x != game.col - 1 &&
          ((game.map(game.player.y)(game.player.x + 1) match {
            case Place() => true
            case _ => false}) ||
            game.player.godMode) => game.player.moveD()
        case 65 if game.player.x != 0 &&
          ((game.map(game.player.y)(game.player.x - 1) match {
            case Place() => true
            case _ => false}) ||
            game.player.godMode) => game.player.moveA()
        case 49 if game.map(game.player.y)(game.player.x) == Place() => game.player.godMode = !game.player.godMode
        case 32 if game.player.godMode => game.map = game.player.createWall(game.map)
        case 81 => game.map(game.player.y)(game.player.x).human = if (game.map(game.player.y)(game.player.x).human.isEmpty) Some(Enemy()) else None
        case 69 => scala.sys.exit(0)
        case _ =>
      }
    }
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    repaint()
  }

  override def paint(g: Graphics): Unit = {
    drawField(g)
  }

  private def drawField(g: Graphics): Unit =  {
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, info.width, info.height)

    for {i <- game.map.zipWithIndex
         j <- i._1.zipWithIndex} {
      j._1 match {
        case Wall() => g.drawImage(j._1.img, j._2 * xSize + 10, i._2 * ySize + 10, xSize, ySize, this)
        case _ =>
          g.setColor(Color.BLACK)
          g.fillRect(j._2 * xSize + 10, i._2 * ySize + 10, xSize, ySize)
          if (j._1.human.isDefined)
            g.drawImage(j._1.human.get.img , j._2 * xSize + 10, i._2 * ySize + 10, xSize, ySize, this)
      }
    }
    g.drawImage(game.player.img, game.player.x * xSize + 10, game.player.y * ySize + 10, xSize, ySize, this)
    if (game.player.godMode) {
      g.setColor(Color.RED)
      g.fillRect(10, info.height - 100, 90, 90)
    }
  }
}
