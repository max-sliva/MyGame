import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.KeyEvent
import java.lang.Thread.sleep
import javax.swing.JPanel

class MovingObject(val pressedKeys: HashSet<Int>) : Runnable, JPanel() {
    private var isRunning = true
    private var x = 0
    private var y = 0
    var moving = ""
    //val pressedKeys: HashSet<Int> = HashSet()
//todo сделать поле с прямоугольными препятствиями, проверять на соприкосновение с объектом, чтоб не двигался при касании
    override fun paint(g: Graphics) {
        super.paint(g)
        val g2d = g as Graphics2D
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )
        g2d.fillOval(x, y, 30, 30)
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
        repaint()
    }


    private fun moveDown(i: Int) {
        y+=i
        checkMoving("down")
        repaint()
    }

    private fun moveLeft(i: Int) {
        x-=i
        checkMoving("left")
        repaint()
    }

    private fun moveUp(i: Int) {
        y-=i
        checkMoving("up")
        repaint()
    }

    override fun run() {
        while (isRunning){
//            if (pressedKeys.contains(KeyEvent.VK_UP)) moveUp(1)
//            if (pressedKeys.contains(KeyEvent.VK_LEFT)) moveLeft(1)
//            if (pressedKeys.contains(KeyEvent.VK_DOWN)) moveDown(1)
//            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) moveRight(1)
            if (pressedKeys.contains(KeyEvent.VK_UP) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) moveUp(1)
            if (pressedKeys.contains(KeyEvent.VK_LEFT) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) moveLeft(1)
            if (pressedKeys.contains(KeyEvent.VK_DOWN) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) moveDown(1)
            if (pressedKeys.contains(KeyEvent.VK_RIGHT) && !pressedKeys.contains(KeyEvent.VK_SHIFT)) moveRight(1)
            if (pressedKeys.contains(KeyEvent.VK_UP) && pressedKeys.contains(KeyEvent.VK_SHIFT)) moveUp(3)
            if (pressedKeys.contains(KeyEvent.VK_LEFT) && pressedKeys.contains(KeyEvent.VK_SHIFT)) moveLeft(3)
            if (pressedKeys.contains(KeyEvent.VK_DOWN) && pressedKeys.contains(KeyEvent.VK_SHIFT)) moveDown(3)
            if (pressedKeys.contains(KeyEvent.VK_RIGHT) && pressedKeys.contains(KeyEvent.VK_SHIFT)) moveRight(3)
            sleep(50)
        }
    }
}