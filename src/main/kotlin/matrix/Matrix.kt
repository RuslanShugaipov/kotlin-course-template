package matrix

class Matrix(
    val m: Int,
    val n: Int,
    val matrix: Array<Array<Double>> = Array(m, { Array(n, { 0.0 }) })
) {

    init {
        if (m <= 0) {
            error("Incorrect number of rows of the matrix!")
        }
        if (n <= 0) {
            error("Incorrect number of rows of the matrix!")
        }
        var isMatch = false
        for (rowIndex in 0 until m) {
            if (matrix[rowIndex].size != n)
                break
            if (rowIndex == matrix.size - 1) {
                isMatch = true
            }
        }
        if (isMatch) {
            for (rowIndex in 0 until m) {
                for (columnIndex in 0 until n)
                    set(rowIndex, columnIndex, matrix[rowIndex][columnIndex])
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
        val sum = Matrix(m, n)
        if (other.m == m && other.n== n) {
            for (rowIndex in 0 until m) {
                for (columnIndex in 0 until n) {
                    sum[rowIndex, columnIndex] = get(rowIndex, columnIndex) + other[rowIndex, columnIndex]
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
        return sum
    }

    operator fun minus(other: Matrix): Matrix {
        val difference = Matrix(m, n)
        if (other.m == m && other.n == n) {
            for (rowIndex in 0 until m) {
                for (columnIndex in 0 until n) {
                    difference[rowIndex, columnIndex] = get(rowIndex, columnIndex) - other[rowIndex, columnIndex]
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
        return difference
    }

    operator fun times(scalar: Double): Matrix {
        val product = Matrix(m, n)
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
                product[rowIndex, columnIndex] = get(rowIndex, columnIndex) * scalar
            }
        }
        return product
    }

    operator fun div(scalar: Double): Matrix {
        if(scalar == 0.0)
            error("Division by zero!")
        val quotient = Matrix(m, n)
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
                quotient[rowIndex, columnIndex] = get(rowIndex, columnIndex) / scalar
            }
        }
        return quotient
    }

    operator fun plusAssign(other: Matrix) {
        if (other.m == m && other.n == n) {
            for (rowIndex in 0 until m) {
                for (columnIndex in 0 until n) {
                    set(rowIndex, columnIndex, get(rowIndex, columnIndex) + other[rowIndex, columnIndex])
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
    }

    operator fun minusAssign(other: Matrix) {
        if (other.m == m && other.n == n) {
            for (rowIndex in 0 until m) {
                for (columnIndex in 0 until n) {
                    set(rowIndex, columnIndex, get(rowIndex, columnIndex) - other[rowIndex, columnIndex])
                }
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
    }

    operator fun timesAssign(scalar: Double) {
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
                set(rowIndex, columnIndex, get(rowIndex, columnIndex) * scalar)
            }
        }
    }

    operator fun divAssign(scalar: Double) {
        if(scalar == 0.0)
            error("Division by zero!")
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
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
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
                elements += "${get(rowIndex, columnIndex)}"
                if(columnIndex < n - 1)
                    elements += " "
            }
            if(rowIndex < m - 1)
                elements += '\n'
        }
        return elements
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (m != other.m) return false
        if (n != other.n) return false
        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = m
        result = 31 * result + n
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }
}