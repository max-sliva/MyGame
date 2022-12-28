package BreakoutGame

import javax.swing.JFrame

fun main(){
    println("Game")
    val gameFrame = JFrame("Breakout").apply {
        add(MainBoard())
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setLocationRelativeTo(null)
        isResizable = false
        pack()
        isVisible = true;
    }
}

interface Commons {
    companion object {
        const val WIDTH = 300
        const val HEIGHT = 400
        const val BOTTOM_EDGE = 390
        const val N_OF_BRICKS = 12
        const val INIT_PADDLE_X = 200
        const val INIT_PADDLE_Y = 360
        const val INIT_BALL_X = 230
        const val INIT_BALL_Y = 355
        const val PERIOD = 5
    }
}


