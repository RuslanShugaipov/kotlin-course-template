//import alignment.*
//import calculator.*
//import shape.*
import matrix.*

fun main() {
    try {
//        Code for the alignment (lab 1)
//        writeTextToFile("Align.txt", alignTextLeft("Something", 8))

//        Code for the calculator (lab 2)
//        println("Result: ${calculate(getRPN("3+1"))}")

//        Code for the shape (lab 3)
//        val triangle = ShapeFactorImpl().createTriangle(3.0, 4.0, 5.0)
//        val randomCircle = ShapeFactorImpl().createRandomCircle()
//        val randomShape = ShapeFactorImpl().createRandomShape()
//        println("The total area of all figures: (${triangle.calcArea() + randomCircle.calcArea() + randomShape.calcArea()})")
//        println("The total perimeter of all figures: (${triangle.calcPerimeter() + randomCircle.calcPerimeter() + randomShape.calcPerimeter()})")
//        when (val maxArea = maxOf(triangle.calcArea(), randomCircle.calcArea(), randomShape.calcArea())) {
//            triangle.calcArea() -> println("The ${triangle.getShapeName()} has the largest area (${maxArea})")
//            randomCircle.calcArea() -> println("The ${randomCircle.getShapeName()} has the largest area (${maxArea})")
//            randomShape.calcArea() -> println("The ${randomShape.getShapeName()} has the largest area (${maxArea})")
//        }
//        when (val minPerimeter =
//            minOf(triangle.calcPerimeter(), randomCircle.calcPerimeter(), randomShape.calcPerimeter())) {
//            triangle.calcPerimeter() -> println("The ${triangle.getShapeName()} has the smallest perimeter (${minPerimeter})")
//            randomCircle.calcPerimeter() -> println("The ${randomCircle.getShapeName()} has the smallest perimeter (${minPerimeter})")
//            randomShape.calcPerimeter() -> println("The ${randomShape.getShapeName()} has the smallest perimeter (${minPerimeter})")
//        }

//        Code for the matrix (lab 4)
        val matrixA = Matrix(3, 3)
        matrixA.setValues(
            arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        val matrixB = Matrix(3, 3)
        matrixB.setValues(
            arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        matrixA[2, 1] = 9.0
        println(matrixA[2, 1])
        println("Dimension of the matrix (n x m): ${matrixA.getDimension()[0]} x ${matrixA.getDimension()[1]}")
        println(matrixB[2, 1])
        println(matrixA == matrixB)
        print(matrixA.toString())
    } catch (message: Exception) {
        println(message)
    }
}