//import alignment.*
//import calculator.*
import shape.*

fun main() {
    try {
//        Code for the alignment (lab 1)
//        writeTextToFile("Align.txt", alignTextLeft("Something", 8))

//        Code for the calculator (lab 2)
//        println("Result: ${calculate(getRPN("3+1"))}")

//        Code for the shape (lab 3)
        val triangle = ShapeFactorImpl().createTriangle(3.0, 4.0, 5.0)
        val randomCircle = ShapeFactorImpl().createRandomCircle()
        val randomShape = ShapeFactorImpl().createRandomShape()
        println("The total area of all figures: (${triangle.calcArea() + randomCircle.calcArea() + randomShape.calcArea()})")
        println("The total perimeter of all figures: (${triangle.calcPerimeter() + randomCircle.calcPerimeter() + randomShape.calcPerimeter()})")
        when (val maxArea = maxOf(triangle.calcArea(), randomCircle.calcArea(), randomShape.calcArea())) {
            triangle.calcArea() -> println("The ${triangle.getShapeName()} has the largest area (${maxArea})")
            randomCircle.calcArea() -> println("The ${randomCircle.getShapeName()} has the largest area (${maxArea})")
            randomShape.calcArea() -> println("The ${randomShape.getShapeName()} has the largest area (${maxArea})")
        }
        when (val minPerimeter =
            minOf(triangle.calcPerimeter(), randomCircle.calcPerimeter(), randomShape.calcPerimeter())) {
            triangle.calcPerimeter() -> println("The ${triangle.getShapeName()} has the smallest perimeter (${minPerimeter})")
            randomCircle.calcPerimeter() -> println("The ${randomCircle.getShapeName()} has the smallest perimeter (${minPerimeter})")
            randomShape.calcPerimeter() -> println("The ${randomShape.getShapeName()} has the smallest perimeter (${minPerimeter})")
        }
    } catch (message: Exception) {
        println(message)
    }
}