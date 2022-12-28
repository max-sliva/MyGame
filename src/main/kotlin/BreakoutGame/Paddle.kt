package BreakoutGame

import java.awt.event.KeyEvent
import javax.swing.ImageIcon

class Paddle : Sprite() {
    private var dx = 0

    init {
        initPaddle()
    }

    private fun initPaddle() {
        loadImage()
        imageDimensions
        resetState()
    }

    private fun loadImage() {
        val ii = ImageIcon("paddle.png")
        image = ii.image
        println("paddle width = ${ii.iconWidth}")
    }

    fun move() {
        x += dx
        if (x <= 0) {
            x = 0
        }
        if (x >= Commons.WIDTH - imageWidth) {
            x = Commons.WIDTH - imageWidth
        }
    }

    fun keyPressed(e: KeyEvent) {
        val key = e.keyCode
        if (key == KeyEvent.VK_LEFT) {
            dx = -1
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1
        }
    }

    fun keyReleased(e: KeyEvent) {
        val key = e.keyCode
        if (key == KeyEvent.VK_LEFT) {
            dx = 0
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0
        }
    }

    private fun resetState() {
        x = Commons.INIT_PADDLE_X
        y = Commons.INIT_PADDLE_Y
    }
}