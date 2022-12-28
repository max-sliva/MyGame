package BreakoutGame

import javax.swing.ImageIcon

class Ball : Sprite() {
    private var xdir = 0
    var yDir = 0

    init {
        initBall()
    }

    private fun initBall() {
        xdir = 1
        yDir = -1
        loadImage()
        imageDimensions
        resetState()
    }

    private fun loadImage() {
        val ii = ImageIcon("ball.png")
        image = ii.image
    }

    fun move() {
        x += xdir
        y += yDir
        if (x == 0) {
            setXDir(1)
        }
        if (x == Commons.WIDTH - imageWidth) {
            println(imageWidth)
            setXDir(-1)
        }
        if (y == 0) {
            yDir = 1
        }
    }

    private fun resetState() {
        x = Commons.INIT_BALL_X
        y = Commons.INIT_BALL_Y
    }

    fun setXDir(x: Int) {
        xdir = x
    }
}
