package shape

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        if (allShapes.contains(new))
            error("This item is already stored.")
        allShapes.add(new)
    }

    fun addAll(new: List<T>) {
        new.forEach { item ->
            add(item)
        }
    }

    fun getAll(): List<T> {
        return allShapes
    }

    fun getAllSorted(comparator: Comparator<T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

    fun getAllByClass(className: Class<out T>): List<T> {
        val result = mutableListOf<T>()
        allShapes.forEach { item ->
            if (item::class.java == className)
                result.add(item)
        }
        return result.toList()
    }
}

class ShapeComparators {
    fun <T : Shape> sortAreaDesc() = Comparator<T> { shape1, shape2 ->
        when {
            (shape1.calcArea() > shape2.calcArea()) -> -1
            (shape1.calcArea() == shape2.calcArea()) -> 0
            else -> 1
        }
    }

    fun <T : Shape> sortAreaAsc() = Comparator<T> { shape1, shape2 ->
        when {
            (shape1.calcArea() < shape2.calcArea()) -> -1
            (shape1.calcArea() == shape2.calcArea()) -> 0
            else -> 1
        }
    }

    fun <T : Shape> sortPerimeterDesc() = Comparator<T> { shape1, shape2 ->
        when {
            (shape1.calcPerimeter() > shape2.calcPerimeter()) -> -1
            (shape1.calcPerimeter() == shape2.calcPerimeter()) -> 0
            else -> 1
        }
    }

    fun <T : Shape> sortPerimeterAsc() = Comparator<T> { shape1, shape2 ->
        when {
            (shape1.calcPerimeter() < shape2.calcPerimeter()) -> -1
            (shape1.calcPerimeter() == shape2.calcPerimeter()) -> 0
            else -> 1
        }
    }

    fun sortRadiusDesc() = Comparator<Circle> { circle1, circle2 ->
        when {
            (circle1.radius > circle2.radius) -> -1
            (circle1.radius == circle2.radius) -> 0
            else -> 1
        }
    }

    fun sortRadiusAsc() = Comparator<Circle> { circle1, circle2 ->
        when {
            (circle1.radius < circle2.radius) -> -1
            (circle1.radius == circle2.radius) -> 0
            else -> 1
        }
    }
}