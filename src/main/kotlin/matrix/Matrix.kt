package matrix

class Matrix(
    _m: Int,
    _n: Int,
) {
    private val m: Int
    private val n: Int
    private val matrix: Array<Array<Double>>

    init {
        if (_m <= 0) {
            error("Incorrect number of rows of the matrix!")
        } else {
            m = _m
        }
        if (_n <= 0) {
            error("Incorrect number of rows of the matrix!")
        } else {
            n = _n
        }
        matrix = Array(m, { Array(n, { 0.0 }) })
    }

    fun setValues(values: Array<Array<Double>>) {
        var isMatch = false
        for (rowIndex in 0 until values.size) {
            if (values[rowIndex].size != n)
                break
            if (rowIndex == values.size - 1) {
                isMatch = true
            }
        }
        if (isMatch) {
            for (rowIndex in 0 until m) {
                for (columnIndex in 0 until n)
                    set(rowIndex, columnIndex, values[rowIndex][columnIndex])
            }
        } else {
            error("The dimensions of the matrices do not match!")
        }
    }

    operator fun get(rowIndex: Int, columnIndex: Int): Double {
        return matrix[rowIndex][columnIndex]
    }

    operator fun set(rowIndex: Int, columnIndex: Int, value: Double) {
        matrix[rowIndex][columnIndex] = value
    }

    fun getDimension(): List<Int> {
        return listOf(m, n)
    }

    operator fun plus(other: Matrix): Matrix {
        val sum = Matrix(m, n)
        if (other.getDimension()[0] == m && other.getDimension()[1] == n) {
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
        if (other.getDimension()[0] == m && other.getDimension()[1] == n) {
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
        val quotient = Matrix(m, n)
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
                if(get(rowIndex, columnIndex) == 0.0)
                    error("Division by zero!")
                quotient[rowIndex, columnIndex] = get(rowIndex, columnIndex) / scalar
            }
        }
        return quotient
    }

    operator fun plusAssign(other: Matrix) {
        if (other.getDimension()[0] == m && other.getDimension()[1] == n) {
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
        if (other.getDimension()[0] == m && other.getDimension()[1] == n) {
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
        for (rowIndex in 0 until m) {
            for (columnIndex in 0 until n) {
                if(get(rowIndex, columnIndex) == 0.0)
                    error("Division by zero!")
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

    override fun equals(other: Any?): Boolean {
        return this.toString() == other.toString()
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

    override fun hashCode(): Int {
        return this.hashCode()
    }
}