import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.JPanel

class Game : JPanel() {
    private var x = 0
    private var y = 0
    var moving = ""
    private fun moveBall() {
        x++
        y++
    }

    override fun paint(g: Graphics) {
        super.paint(g)
        val g2d = g as Graphics2D
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )
        g2d.fillOval(x, y, 30, 30)
    }

    companion object {
        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val pressedKeys: HashSet<Int> = HashSet()

            val frame = JFrame("Mini Tennis")
            val game = Game()
            frame.add(game)
            frame.setSize(300, 400)
            frame.isVisible = true
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.addKeyListener(object : KeyAdapter() {
//                override fun keyTyped(e: KeyEvent) {
//                    println("key typed")
//                }

                override fun keyPressed(e: KeyEvent) {
                    super.keyPressed(e)

                    if (!pressedKeys.contains(e.keyCode)) {
                        pressedKeys.add(e.keyCode)
                        println("key pressed ${e.keyCode}")
                        println("pressedKeys =  ${pressedKeys}")
                    }
                    if (pressedKeys.contains(KeyEvent.VK_UP) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveUp(1)
                    if (pressedKeys.contains(KeyEvent.VK_LEFT) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveLeft(1)
                    if (pressedKeys.contains(KeyEvent.VK_DOWN) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveDown(1)
                    if (pressedKeys.contains(KeyEvent.VK_RIGHT) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveRight(1)
                    if (pressedKeys.contains(KeyEvent.VK_UP) && pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveUp(3)
                    if (pressedKeys.contains(KeyEvent.VK_LEFT) && pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveLeft(3)
                    if (pressedKeys.contains(KeyEvent.VK_DOWN) && pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveDown(3)
                    if (pressedKeys.contains(KeyEvent.VK_RIGHT) && pressedKeys.contains(KeyEvent.VK_SHIFT)) game.moveRight(3)
                    game.repaint()
                }

                override fun keyReleased(e: KeyEvent) {
//                    super.keyReleased(e)
                    println("pressedKeys removes ${e.keyCode}")
                    pressedKeys.remove(e.keyCode)
                    println("pressedKeys =  ${pressedKeys}")
                }
            })
//            while (true) {
//                game.moveBall()
//                game.repaint()
//                Thread.sleep(10)
//            }
        }
    }

    private fun checkMoving(s: String) {
        if (moving!=s) {
            moving = s
            println(s)
        }
    }

    private fun moveRight(i: Int) {
        x+=i
        checkMoving("right")
    }


    private fun moveDown(i: Int) {
        y+=i
        checkMoving("down")
    }

    private fun moveLeft(i: Int) {
        x-=i
        checkMoving("left")
    }

    private fun moveUp(i: Int) {
        y-=i
        checkMoving("up")
    }
}