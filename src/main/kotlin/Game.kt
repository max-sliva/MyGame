import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel


class Game : JPanel() {
    private var x = 0
    private var y = 0
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
            val frame = JFrame("Mini Tennis")
            val game = Game()
            frame.add(game)
            frame.setSize(300, 400)
            frame.isVisible = true
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.addKeyListener(object : KeyAdapter() {
                override fun keyTyped(e: KeyEvent) {
                    println("key typed")
                }

                override fun keyPressed(e: KeyEvent) {
                    println("key pressed")
                    if (e.keyCode == KeyEvent.VK_UP) game.moveUp()
                    if (e.keyCode == KeyEvent.VK_LEFT) game.moveLeft()
                    if (e.keyCode == KeyEvent.VK_DOWN) game.moveDown()
                    if (e.keyCode == KeyEvent.VK_RIGHT) game.moveRight()
                    game.repaint()
                }
            })
//            while (true) {
//                game.moveBall()
//                game.repaint()
//                Thread.sleep(10)
//            }
        }
    }

    private fun moveRight() {
        x++
    }

    private fun moveDown() {
        y++
    }

    private fun moveLeft() {
        x--
    }

    private fun moveUp() {
        y--
    }
}