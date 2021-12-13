package matrix

class Matrix(
    initMatrix: Array<Array<Double>>
) {
    private val matrix = Array(initMatrix.size){Array(initMatrix[0].size) {0.0} }
    init {
        var isMatch = false
        for (rowIndex in 0 until matrix.size) {
            if (matrix[rowIndex].size != matrix[0].size)
                break
            if (rowIndex == matrix.size - 1) {
                isMatch = true
            }
        }
        if (isMatch) {
            for (rowIndex in 0 until matrix.size) {
                for (columnIndex in 0 until matrix[0].size)
                    set(rowIndex, columnIndex, initMatrix[rowIndex][columnIndex])
            }
        } else {
            error("Invalid initialization!")
        }
    }
    operator fun get(rowIndex: Int, columnIndex: Int): Double {
        return matrix[rowIndex][columnIndex]
    }

    operator fun set(rowIndex: Int, columnIndex: Int, value: Double) {
        matrix[rowIndex][columnIndex] = value
    }

    operator fun plus(other: Matrix): Matrix {
        val sum = Matrix(other.matrix)
        if (other.matrix.size == matrix.size && other.matrix[0].size == matrix[0].size) {
            for (rowIndex in 0 until other.matrix.size) {
                for (columnIndex in 0 until other.matrix[0].size) {
                    sum[rowIndex, columnIndex] = get(rowIndex, columnIndex) + other[rowIndex, columnIndex]
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
        return sum
    }

    operator fun minus(other: Matrix): Matrix {
        val difference = Matrix(other.matrix)
        if (other.matrix.size == matrix.size && other.matrix[0].size == matrix[0].size) {
            for (rowIndex in 0 until other.matrix.size) {
                for (columnIndex in 0 until other.matrix[0].size) {
                    difference[rowIndex, columnIndex] = get(rowIndex, columnIndex) - other[rowIndex, columnIndex]
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
        return difference
    }

    operator fun times(scalar: Double): Matrix {
        val product = Matrix(matrix)
            for (rowIndex in 0 until matrix.size) {
                for (columnIndex in 0 until matrix[0].size) {
                product[rowIndex, columnIndex] = get(rowIndex, columnIndex) * scalar
            }
        }
        return product
    }

    operator fun div(scalar: Double): Matrix {
        if(scalar == 0.0)
            error("Division by zero!")
        val quotient = Matrix(matrix)
            for (rowIndex in 0 until matrix.size) {
                for (columnIndex in 0 until matrix[0].size) {
                quotient[rowIndex, columnIndex] = get(rowIndex, columnIndex) / scalar
            }
        }
        return quotient
    }

    operator fun plusAssign(other: Matrix) {
        if (other.matrix.size == matrix.size && other.matrix[0].size == matrix[0].size) {
            for (rowIndex in 0 until other.matrix.size) {
                for (columnIndex in 0 until other.matrix[0].size) {
                    set(rowIndex, columnIndex, get(rowIndex, columnIndex) + other[rowIndex, columnIndex])
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
    }

    operator fun minusAssign(other: Matrix) {
        if (other.matrix.size == matrix.size && other.matrix[0].size == matrix[0].size) {
            for (rowIndex in 0 until other.matrix.size) {
                for (columnIndex in 0 until other.matrix[0].size) {
                    set(rowIndex, columnIndex, get(rowIndex, columnIndex) - other[rowIndex, columnIndex])
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
    }

    operator fun timesAssign(scalar: Double) {
            for (rowIndex in 0 until matrix.size) {
                for (columnIndex in 0 until matrix[0].size) {
                set(rowIndex, columnIndex, get(rowIndex, columnIndex) * scalar)
            }
        }
    }

    operator fun divAssign(scalar: Double) {
        if(scalar == 0.0)
            error("Division by zero!")
            for (rowIndex in 0 until matrix.size) {
                for (columnIndex in 0 until matrix[0].size) {
                set(rowIndex, columnIndex, get(rowIndex, columnIndex) / scalar)
            }
        }
    }

    operator fun unaryMinus(): Matrix {
        return this.times(-1.0)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    override fun toString(): String {
        var elements = ""
            for (rowIndex in 0 until matrix.size) {
                for (columnIndex in 0 until matrix[0].size) {
                elements += "${get(rowIndex, columnIndex)}"
                if(columnIndex < matrix[0].size - 1)
                    elements += " "
            }
            if(rowIndex < matrix.size - 1)
                elements += '\n'
        }
        return elements
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (matrix.size != other.matrix.size) return false
        if (matrix[0].size != other.matrix[0].size) return false
        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = matrix.size
        result = 31 * result + matrix[0].size
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }
}