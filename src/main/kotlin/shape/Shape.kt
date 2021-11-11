package shape

import kotlin.math.PI
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
    fun getShapeName(): String
}

class Circle(
    _radius: Double
) : Shape {
    private val radius: Double

    init {
        if (_radius <= 0) {
            error("Incorrect radius value!")
        } else {
            radius = _radius
        }
    }

    override fun calcArea(): Double {
        return PI * radius * radius
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * radius
    }

    override fun getShapeName(): String {
        return "Cirlce"
    }
}

class Square(
    _side: Double
) : Shape {
    private val side: Double

    init {
        if (_side <= 0) {
            error("Incorrect side value!")
        } else {
            side = _side
        }
    }

    override fun calcArea(): Double {
        return side * side
    }

    override fun calcPerimeter(): Double {
        return 4 * side
    }

    override fun getShapeName(): String {
        return "Square"
    }
}

class Rectangle(
    _length: Double,
    _width: Double
) : Shape {
    private val length: Double
    private val width: Double

    init {
        if (_length <= 0) {
            error("Incorrect length value!")
        } else {
            length = _length
        }
        if (_width <= 0) {
            error("Incorrect width value!")
        } else {
            width = _width
        }
    }

    override fun calcArea(): Double {
        return length * width
    }

    override fun calcPerimeter(): Double {
        return 2 * (length + width)
    }

    override fun getShapeName(): String {
        return "Rectangle"
    }
}

class Triangle(
    _sideA: Double,
    _sideB: Double,
    _sideC: Double
) : Shape {
    private val sideA: Double
    private val sideB: Double
    private val sideC: Double

    init {
        if (_sideA <= 0 || _sideB <= 0 || _sideC <= 0 || ((_sideA + _sideB) < _sideC || (_sideA + _sideC) < _sideB || (_sideB + _sideC) < _sideA)) {
            error("Incorrect side value!")
        } else {
            sideA = _sideA
            sideB = _sideB
            sideC = _sideC
        }
    }

    override fun calcArea(): Double {
        val semiPerimeter = (sideA + sideB + sideC) / 2
        return sqrt(semiPerimeter * (semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }

    override fun getShapeName(): String {
        return "Triangle"
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
        var randomRadius: Double = -1.0
        while (randomRadius <= 0) {
            randomRadius = Random.nextDouble()
        }
        return Circle(randomRadius)
    }

    override fun createRandomSquare(): Square {
        var randomSide: Double = -1.0
        while (randomSide <= 0) {
            randomSide = Random.nextDouble()
        }
        return Square(randomSide)
    }

    override fun createRandomRectangle(): Rectangle {
        var randomLength: Double = -1.0
        var randomWidth: Double = -1.0
        while (randomLength <= 0) {
            randomLength = Random.nextDouble()
        }
        while (randomWidth <= 0) {
            randomWidth = Random.nextDouble()
        }
        return Rectangle(randomLength, randomWidth)
    }

    override fun createRandomTriangle(): Triangle {
        var randomSideA: Double = -1.0
        var randomSideB: Double = -1.0
        var randomSideC: Double = -1.0
        while ((randomSideA + randomSideB) < randomSideC || (randomSideA + randomSideC) < randomSideB || (randomSideB + randomSideC) < randomSideA) {
            while (randomSideA <= 0) {
                randomSideA = Random.nextDouble()
            }
            while (randomSideB <= 0) {
                randomSideB = Random.nextDouble()
            }
            while (randomSideC <= 0) {
                randomSideC = Random.nextDouble()
            }
        }
        return Triangle(randomSideA, randomSideB, randomSideC)
    }

    override fun createRandomShape(): Shape {
        when (Random.nextInt(1, 4)) {
            1 -> return createRandomCircle()
            2 -> return createRandomSquare()
            3 -> return createRandomRectangle()
            4 -> return createRandomTriangle()
            else -> return createRandomShape()
        }
    }
}