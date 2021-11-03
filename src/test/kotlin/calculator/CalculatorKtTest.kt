package calculator

import alignment.alignTextLeft
import org.junit.Assert
import org.junit.Test
import java.util.*

internal class CalculatorKtTest{

    val input1 = "1+(4-2)*6"
    val input2 = "( 3+ (4*2)/  ( ((1-5))^ -2))"
    val input3 = "(-3-(-10)-8)"
    val input4 = "(4+3)*9/+4"
    val input5 = "+5-4"
    val input6 = "-(-(5+4))"
    val input7 = "sin(ln(cos(5+1)^sin(11-2*1)))"
    val input8 = "(sin(5)a)b)c)"

    //Checking getRPN function
    @Test
    fun `checking the correctness of the conversion (input1)`() {
        Assert.assertEquals("1 4 2 - 6 * +", getRPN(input1))
    }
    @Test
    fun `checking the correctness of the conversion (input2)`() {
        Assert.assertEquals("3 4 2 * 1 5 - 2 u- ^ / +", getRPN(input2))
    }
    @Test
    fun `checking the correctness of the conversion (input3)`() {
        Assert.assertEquals("3 u- 10 u- - 8 -", getRPN(input3))
    }
    @Test
    fun `checking the correctness of the conversion (input4)`() {
        Assert.assertEquals("4 3 + 9 * 4 u+ /", getRPN(input4))
    }
    @Test
    fun `checking the correctness of the conversion (input5)`() {
        Assert.assertEquals("5 u+ 4 -", getRPN(input5))
    }
    @Test
    fun `checking the correctness of the conversion (input6)`() {
        Assert.assertEquals("5 4 + u- u-", getRPN(input6))
    }
    @Test
    fun `checking the correctness of the conversion (input7)`() {
        Assert.assertEquals("5 1 + cos 11 2 1 * - sin ^ ln sin", getRPN(input7))
    }

    //Checking calculate function
    @Test
    fun `checking the correctness of the calculation (input1)`() {
        Assert.assertEquals(13.0f, calculate(getRPN(input1)))
    }
    @Test
    fun `checking the correctness of the calculation (input2)`() {
        Assert.assertEquals(131.0f, calculate(getRPN(input2)))
    }
    @Test
    fun `checking the correctness of the calculation (input3)`() {
        Assert.assertEquals(-1.0f, calculate(getRPN(input3)))
    }
    @Test
    fun `checking the correctness of the calculation (input4)`() {
        Assert.assertEquals(15.75f, calculate(getRPN(input4)))
    }
    @Test
    fun `checking the correctness of the calculation (input5)`() {
        Assert.assertEquals(1.0f, calculate(getRPN(input5)))
    }
    @Test
    fun `checking the correctness of the calculation (input6)`() {
        Assert.assertEquals(9.0f, calculate(getRPN(input6)))
    }
    @Test
    fun `checking the correctness of the calculation (input7)`() {
        Assert.assertEquals(-0.016749645f, calculate(getRPN(input7)))
    }

    //Checking for incorrect input expression
    @Test
    fun `checking for incorrect input expression (getRPN)`() {
        Assert.assertEquals(null, getRPN(input8))
    }

    @Test
    fun `checking for incorrect input expression (calculate)`() {
        Assert.assertEquals(null, calculate(getRPN(input8)))
    }
}