import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame

fun main(){
    val pressedKeys: HashSet<Int> = HashSet()

    val frame = JFrame("KoloBall")
    val movingBall = MovingObject(pressedKeys)
    frame.add(movingBall)
    frame.setSize(300, 400)
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.addKeyListener(object : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            super.keyPressed(e)
            if (!pressedKeys.contains(e.keyCode)) {
                pressedKeys.add(e.keyCode)
                println("key pressed ${e.keyCode}")
                println("pressedKeys =  ${pressedKeys}")
            }

        }

        override fun keyReleased(e: KeyEvent) {
//                    super.keyReleased(e)
            println("pressedKeys removes ${e.keyCode}")
            pressedKeys.remove(e.keyCode)
            println("pressedKeys =  ${pressedKeys}")
        }
    })
    val myThread = Thread(movingBall).apply { start() }
}