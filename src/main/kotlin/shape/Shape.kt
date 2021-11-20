package shape

import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(
    val radius: Double
) : Shape {
    init {
        if (radius <= 0) {
            error("Incorrect radius value!")
        }
    }

    override fun calcArea(): Double {
        return PI * radius * radius
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * radius
    }
}

class Square(
    val side: Double
) : Shape {
    init {
        if (side <= 0) {
            error("Incorrect side value!")
        }
    }

    override fun calcArea(): Double {
        return side * side
    }

    override fun calcPerimeter(): Double {
        return 4 * side
    }
}

class Rectangle(
    val length: Double,
    val width: Double
) : Shape {
    init {
        if (length <= 0) {
            error("Incorrect length value!")
        }
        if (width <= 0) {
            error("Incorrect width value!")
        }
    }

    override fun calcArea(): Double {
        return length * width
    }

    override fun calcPerimeter(): Double {
        return 2 * (length + width)
    }
}

class Triangle(
    val sideA: Double,
    val sideB: Double,
    val sideC: Double
) : Shape {
    init {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0 || ((sideA + sideB) < sideC || (sideA + sideC) < sideB || (sideB + sideC) < sideA)) {
            error("Incorrect side value!")
        }
    }

    override fun calcArea(): Double {
        val semiPerimeter = (sideA + sideB + sideC) / 2
        return sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(side: Double): Square
    fun createRectangle(length: Double, width: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactorImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(side: Double): Square {
        return Square(side)
    }

    override fun createRectangle(length: Double, width: Double): Rectangle {
        return Rectangle(length, width)
    }

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle {
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomCircle(): Circle {
        return Circle(Random.nextDouble(0.0, Double.MAX_VALUE))
    }

    override fun createRandomSquare(): Square {
        return Square(Random.nextDouble(0.0, Double.MAX_VALUE))
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(Random.nextDouble(0.0, Double.MAX_VALUE), Random.nextDouble(0.0, Double.MAX_VALUE))
    }

    override fun createRandomTriangle(): Triangle {
        return Triangle(Random.nextDouble(0.0, Double.MAX_VALUE), Random.nextDouble(0.0, Double.MAX_VALUE), Random.nextDouble(0.0, Double.MAX_VALUE))
    }

    override fun createRandomShape(): Shape {
        return when (Random.nextInt(1, 5)) {
            1 -> createRandomCircle()
            2 -> createRandomSquare()
            3 -> createRandomRectangle()
            else -> createRandomTriangle()
        }
    }
}