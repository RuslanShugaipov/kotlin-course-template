//import alignment.*
//import calculator.*
//import matrix.*
//import library.*
import shape.*

fun main() {
    try {
        //Code for the lab 6
        val triangle = ShapeFactorImpl().createTriangle(3.0, 4.0, 5.0)
        val circle1 = ShapeFactorImpl().createCircle(90.0)
        val circle2 = ShapeFactorImpl().createCircle(8.0)
        val randomCircle = ShapeFactorImpl().createRandomCircle()
        val randomShape = ShapeFactorImpl().createRandomShape()
        //print area
        println("The area of the ${triangle::class.java.simpleName}: (${triangle.calcArea()})")
        println("The area of the ${circle1::class.java.simpleName}: (${circle1.calcArea()})")
        println("The area of the ${circle2::class.java.simpleName}: (${circle2.calcArea()})")
        println("The area of the ${randomCircle::class.java.simpleName}: (${randomCircle.calcArea()})")
        println("The area of the ${randomShape::class.java.simpleName}: (${randomShape.calcArea()})")
        //print perimeter
        println("The perimeter of the ${triangle::class.java.simpleName}: (${triangle.calcPerimeter()})")
        println("The perimeter of the ${circle1::class.java.simpleName}: (${circle1.calcPerimeter()})")
        println("The perimeter of the ${circle2::class.java.simpleName}: (${circle2.calcPerimeter()})")
        println("The perimeter of the ${randomCircle::class.java.simpleName}: (${randomCircle.calcPerimeter()})")
        println("The perimeter of the ${randomShape::class.java.simpleName}: (${randomShape.calcPerimeter()})")


        val newShapeCollector = ShapeCollector<Shape>()
        val newShapeComparator = ShapeComparators()
        val shapes = mutableListOf(triangle, randomCircle, randomShape)
        newShapeCollector.addAll(shapes)
        val sortedBy = newShapeCollector.getAllSorted(newShapeComparator.sortPerimeterAsc())
        val sortedByClass = newShapeCollector.getAllByClass(Triangle::class.java)
        for (item in sortedBy)
            println("The area of the ${item::class.java.simpleName}: (${item.calcArea()})")
        for (item in sortedBy)
            println("The perimeter of the ${item::class.java.simpleName}: (${item.calcPerimeter()})")

        val newCircleCollector = ShapeCollector<Circle>()
        val circles = mutableListOf(circle1, circle2, randomCircle)
        newCircleCollector.addAll(circles)
        val sortedByRadius = newCircleCollector.getAllSorted(newShapeComparator.sortRadiusDesc())
        for (item in sortedByRadius)
            println("The radius of the ${item::class.java.simpleName}: (${item.radius})")
    } catch (message: Exception) {
        println(message)
    }
}