package BreakoutGame

import javax.swing.ImageIcon

class Brick(x: Int, y: Int) : Sprite() {
    var isDestroyed = false

    init {
        initBrick(x, y)
    }

    private fun initBrick(x: Int, y: Int) {
        this.x = x
        this.y = y
        isDestroyed = false
        loadImage()
        imageDimensions
    }

    private fun loadImage() {
        val ii = ImageIcon("brick.png")
        image = ii.image
    }
}