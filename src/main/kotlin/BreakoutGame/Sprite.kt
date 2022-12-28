package BreakoutGame

import java.awt.Image
import java.awt.Rectangle


open class Sprite {
    var x = 0
        protected set
    var y = 0
        protected set
    var imageWidth = 0
    var imageHeight = 0
    var image: Image? = null
    val rect: Rectangle
        get() = Rectangle(
            x, y,
            image!!.getWidth(null), image!!.getHeight(null)
        )
    val imageDimensions: Unit
        get() {
            imageWidth = image!!.getWidth(null)
            imageHeight = image!!.getHeight(null)
        }
}
