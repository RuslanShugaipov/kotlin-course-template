package shape

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
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

    //обеспечивается контрвариантность
    fun getAllSorted(comparator: Comparator<in T>): List<T> {
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
        shape2.calcArea().compareTo(shape1.calcArea())
    }

    fun <T : Shape> sortAreaAsc() = Comparator<T> { shape1, shape2 ->
        shape1.calcArea().compareTo(shape2.calcArea())
    }

    fun <T : Shape> sortPerimeterDesc() = Comparator<T> { shape1, shape2 ->
        shape2.calcPerimeter().compareTo(shape1.calcPerimeter())
    }

    fun <T : Shape> sortPerimeterAsc() = Comparator<T> { shape1, shape2 ->
        shape1.calcPerimeter().compareTo(shape2.calcPerimeter())
    }

    fun sortRadiusDesc() = Comparator<Circle> { circle1, circle2 ->
        circle2.radius.compareTo(circle1.radius)
    }

    fun sortRadiusAsc() = Comparator<Circle> { circle1, circle2 ->
        circle1.radius.compareTo(circle2.radius)
    }
}