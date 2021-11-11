package shape

import org.junit.Assert
import org.junit.Test

class ShapeKtTest {
    //    Circle tests
    @Test
    fun `check calcArea with correct value (Circle)`() {
        Assert.assertEquals(78.53981633974483, Circle(5.0).calcArea(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcArea with incorrect value (Circle)`() {
        Circle(-5.0).calcArea()
    }

    @Test
    fun `check calcPerimeter with correct value (Circle)`() {
        Assert.assertEquals(31.41592653589793, Circle(5.0).calcPerimeter(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcPerimeter with incorrect value (Circle)`() {
        Circle(-5.0).calcPerimeter()
    }

    @Test(expected = IllegalStateException::class)
    fun `check createCircle with incorrect value (Circle)`() {
        ShapeFactorImpl().createCircle(-5.0)
    }

    //    Square tests
    @Test
    fun `check calcArea with correct value (Square)`() {
        Assert.assertEquals(25.0, Square(5.0).calcArea(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcArea with incorrect value (Square)`() {
        Square(-5.0).calcArea()
    }

    @Test
    fun `check calcPerimeter with correct value (Square)`() {
        Assert.assertEquals(20.0, Square(5.0).calcPerimeter(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcPerimeter with incorrect value (Square)`() {
        Square(-5.0).calcPerimeter()
    }

    @Test(expected = IllegalStateException::class)
    fun `check createCircle with incorrect value (Square)`() {
        ShapeFactorImpl().createCircle(-5.0)
    }

    //    Rectangle tests
    @Test
    fun `check calcArea with correct value (Rectangle)`() {
        Assert.assertEquals(20.0, Rectangle(5.0, 4.0).calcArea(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcArea with incorrect value (Rectangle)`() {
        Rectangle(-5.0, 0.0).calcArea()
    }

    @Test
    fun `check calcPerimeter with correct value (Rectangle)`() {
        Assert.assertEquals(18.0, Rectangle(5.0, 4.0).calcPerimeter(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcPerimeter with incorrect value (Rectangle)`() {
        Rectangle(-5.0, 0.0).calcPerimeter()
    }

    @Test(expected = IllegalStateException::class)
    fun `check createCircle with incorrect value (Rectangle)`() {
        ShapeFactorImpl().createRectangle(-5.0, 0.0)
    }

    //    Triangle tests
    @Test
    fun `check calcArea with correct value (Triangle)`() {
        Assert.assertEquals(6.0, Triangle(3.0, 4.0, 5.0).calcArea(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcArea with incorrect value (Triangle)`() {
        Triangle(10.0, 4.0, 5.0).calcArea()
    }

    @Test
    fun `check calcPerimeter with correct value (Triangle)`() {
        Assert.assertEquals(12.0, Triangle(3.0, 4.0, 5.0).calcPerimeter(), 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `check calcPerimeter with incorrect value (Triangle)`() {
        Triangle(10.0, 4.0, 5.0).calcPerimeter()
    }

    @Test(expected = IllegalStateException::class)
    fun `check createCircle with incorrect value (Triangle)`() {
        ShapeFactorImpl().createTriangle(10.0, 4.0, 5.0)
    }
}