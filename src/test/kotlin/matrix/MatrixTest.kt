package matrix

import org.junit.Assert
import org.junit.Test

class MatrixTest {
    private var matrixB = Matrix()
    private var matrixA = Matrix()
    private var matrixC = Matrix()

    @Test(expected = Exception::class)
    fun `checking the overloaded operator + for exception`() {
        matrixB = Matrix( arrayOf(
            arrayOf(0.0, 1.0, 2.0),
            arrayOf(3.0, 4.0, 5.0),
            arrayOf(6.0, 7.0, 8.0, 9.0)
        ))
        matrixC = matrixB + matrixA
    }

    @Test
    fun `checking the overloaded operator +`() {
        val values = arrayOf(
            arrayOf(0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0)
        )
        matrixC = Matrix( arrayOf(
                arrayOf(0.0, -1.0, -2.0),
                arrayOf(-3.0, -4.0, -5.0),
                arrayOf(-6.0, -7.0, -8.0)
            )
        )
        matrixB = Matrix( arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        matrixC = matrixC + matrixB
        var isMatch = false
        for (rowIndex in 0 until 3) {
            for (columnIndex in 0 until 3) {
                if (values[rowIndex][columnIndex] != matrixC[rowIndex, columnIndex])
                    break
                if (rowIndex == values.size - 1) {
                    isMatch = true
                }
            }
        }
        Assert.assertEquals(true, isMatch)
    }

    @Test(expected = Exception::class)
    fun `checking the overloaded operator - for exception`() {
        matrixB = Matrix( arrayOf(
            arrayOf(0.0, 1.0, 2.0),
            arrayOf(3.0, 4.0, 5.0),
            arrayOf(6.0, 7.0, 8.0, 9.0)
        )
        )
        matrixC = matrixB - matrixA
    }

    @Test
    fun `checking the overloaded operator -`() {
        val values = arrayOf(
            arrayOf(0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0),
            arrayOf(0.0, 0.0, 0.0)
        )
        matrixC = Matrix(arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        matrixB = Matrix( arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        matrixC = matrixC - matrixB
        var isMatch = false
        for (rowIndex in 0 until 3) {
            for (columnIndex in 0 until 3) {
                if (values[rowIndex][columnIndex] != matrixC[rowIndex, columnIndex])
                    break
                if (rowIndex == values.size - 1) {
                    isMatch = true
                }
            }
        }
        Assert.assertEquals(true, isMatch)
    }

    @Test
    fun `checking the overloaded operator times`() {
        val values = arrayOf(
            arrayOf(0.0, 2.0, 4.0),
            arrayOf(6.0, 8.0, 10.0),
            arrayOf(12.0, 14.0, 16.0)
        )
        matrixC = Matrix(arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        matrixC = matrixC * 2.0
        var isMatch = false
        for (rowIndex in 0 until 3) {
            for (columnIndex in 0 until 3) {
                if (values[rowIndex][columnIndex] != matrixC[rowIndex, columnIndex])
                    break
                if (rowIndex == values.size - 1) {
                    isMatch = true
                }
            }
        }
        Assert.assertEquals(true, isMatch)
    }

    @Test
    fun `checking the overloaded operator div`() {
        val values = arrayOf(
            arrayOf(0.0, 0.5, 1.0),
            arrayOf(1.5, 2.0, 2.5),
            arrayOf(3.0, 3.5, 4.0)
        )
        matrixC = Matrix(arrayOf(
                arrayOf(0.0, 1.0, 2.0),
                arrayOf(3.0, 4.0, 5.0),
                arrayOf(6.0, 7.0, 8.0)
            )
        )
        matrixC = matrixC / 2.0
        for (rowIndex in 0 until 3) {
            for (columnIndex in 0 until 3) {
                Assert.assertEquals(values[rowIndex][columnIndex], matrixC[rowIndex,columnIndex], 0.0)
            }
        }
    }

    @Test
    fun `checking the toString method`() {
        matrixA = Matrix(arrayOf(
                arrayOf(0.0, 1.0, 2.0, 3.0),
                arrayOf(4.0, 5.0, 6.0, 7.0),
                arrayOf(8.0, 9.0, 10.0, 11.0)
            )
        )
        val stringToCheck = "0.0 1.0 2.0 3.0\n4.0 5.0 6.0 7.0\n8.0 9.0 10.0 11.0"
        Assert.assertEquals(stringToCheck, matrixA.toString())
    }
}