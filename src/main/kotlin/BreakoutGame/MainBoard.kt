package BreakoutGame

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel
import javax.swing.Timer

class MainBoard : JPanel() {
    private var timer: Timer? = null
    private var message = "Game Over"
    private var ball: Ball? = null
    private var paddle: Paddle? = null
    private lateinit var bricks: Array<Brick?>
    private var inGame = true

    init {
        initBoard()
    }

    private fun initBoard() {
        println("Hello")
        addKeyListener(TAdapter())
        isFocusable = true
        preferredSize = Dimension(Commons.WIDTH, Commons.HEIGHT)
        gameInit()
    }

    private fun gameInit() {
        bricks = arrayOfNulls(Commons.N_OF_BRICKS)
        ball = Ball()
        paddle = Paddle()
        var k = 0
//        for (i in 0..4) {
        for (i in 0.. Commons.N_OF_BRICKS / 6) { //цикл по рядам блоков
        //цикл по блокам в ряду
            for (j in 0..if (i==Commons.N_OF_BRICKS / 6) Commons.N_OF_BRICKS-6*i-1 else 5) {
                bricks[k] = Brick(j * 40 + 30, i * 10 + 50)
                k++
            }
        }
        timer = Timer(Commons.PERIOD, GameCycle())
        timer!!.start()
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2d = g as Graphics2D
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )
        g2d.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY
        )
        if (inGame) {
            drawObjects(g2d)
        } else {
            gameFinished(g2d)
        }
        Toolkit.getDefaultToolkit().sync()
    }

    private fun drawObjects(g2d: Graphics2D) {
        g2d.drawImage(
            ball!!.image, ball!!.x, ball!!.y,
            ball!!.imageWidth, ball!!.imageHeight, this
        )
        g2d.drawImage(
            paddle!!.image, paddle!!.x, paddle!!.y,
            paddle!!.imageWidth, paddle!!.imageHeight, this
        )
        for (i in 0 until Commons.N_OF_BRICKS) {
            if (!bricks[i]!!.isDestroyed) {
                g2d.drawImage(
                    bricks[i]!!.image, bricks[i]!!.x,
                    bricks[i]!!.y, bricks[i]!!.imageWidth,
                    bricks[i]!!.imageHeight, this
                )
            }
        }
    }

    private fun gameFinished(g2d: Graphics2D) {
        val font = Font("Verdana", Font.BOLD, 18)
        val fontMetrics = getFontMetrics(font)
        g2d.color = Color.BLACK
        g2d.font = font
        g2d.drawString(
            message,
            (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2,
            Commons.WIDTH / 2
        )
    }

    private inner class TAdapter : KeyAdapter() {
        override fun keyReleased(e: KeyEvent) {
            paddle!!.keyReleased(e)
        }

        override fun keyPressed(e: KeyEvent) {
            paddle!!.keyPressed(e)
        }
    }

    private inner class GameCycle : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            doGameCycle()
        }
    }

    private fun doGameCycle() {
//        println("cycle")
        ball!!.move()
        paddle!!.move()
        checkCollision()
        repaint()
    }

    private fun stopGame() {
        inGame = false
        timer!!.stop()
    }

    private fun checkCollision() {
        if (ball!!.rect.maxY > Commons.BOTTOM_EDGE) {
            stopGame()
        }

            var i = 0
            var j = 0
            while (i < Commons.N_OF_BRICKS) {
                if (bricks[i]!!.isDestroyed) {
                    j++
                }
                if (j == Commons.N_OF_BRICKS) {
                    message = "Victory"
                    stopGame()
                }
                i++
            }

        if (ball!!.rect.intersects(paddle!!.rect)) {
            val paddleLPos = paddle!!.rect.minX.toInt()
            val ballLPos = ball!!.rect.minX.toInt()
            val first = paddleLPos + 8
            val second = paddleLPos + 16
            val third = paddleLPos + 24
            val fourth = paddleLPos + 32
            if (ballLPos < first) {
                ball!!.setXDir(-1)
                ball!!.yDir = -1
            }
            if (ballLPos >= first && ballLPos < second) {
                ball!!.setXDir(-1)
                ball!!.yDir = -1 * ball!!.yDir
            }
            if (ballLPos >= second && ballLPos < third) {
                ball!!.setXDir(0)
                ball!!.yDir = -1
            }
            if (ballLPos >= third && ballLPos < fourth) {
                ball!!.setXDir(1)
                ball!!.yDir = -1 * ball!!.yDir
            }
            if (ballLPos > fourth) {
                ball!!.setXDir(1)
                ball!!.yDir = -1
            }
        }
        for (i in 0 until Commons.N_OF_BRICKS) {
            if (ball!!.rect.intersects(bricks[i]!!.rect)) {
                val ballLeft = ball!!.rect.minX.toInt()
                val ballHeight = ball!!.rect.getHeight().toInt()
                val ballWidth = ball!!.rect.getWidth().toInt()
                val ballTop = ball!!.rect.minY.toInt()
                val pointRight = Point(ballLeft + ballWidth + 1, ballTop)
                val pointLeft = Point(ballLeft - 1, ballTop)
                val pointTop = Point(ballLeft, ballTop - 1)
                val pointBottom = Point(ballLeft, ballTop + ballHeight + 1)
                if (!bricks[i]!!.isDestroyed) {
                    if (bricks[i]!!.rect.contains(pointRight)) {
                        ball!!.setXDir(-1)
                    } else if (bricks[i]!!.rect.contains(pointLeft)) {
                        ball!!.setXDir(1)
                    }
                    if (bricks[i]!!.rect.contains(pointTop)) {
                        ball!!.yDir = 1
                    } else if (bricks[i]!!.rect.contains(pointBottom)) {
                        ball!!.yDir = -1
                    }
                    bricks[i]!!.isDestroyed = true
                }
            }
        }
    }
}
