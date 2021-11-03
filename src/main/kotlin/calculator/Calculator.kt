package calculator

import java.util.*
import kotlin.math.*

//A class containing operations and returning their priorities
class OperationPriority {
    companion object {
        private val Level_5: List<String> = listOf("u-", "u+", "sin", "cos", "tg", "ctg", "lg", "ln")
        private val Level_4: List<String> = listOf("^")
        private val Level_3: List<String> = listOf("*", "/")
        private val Level_2: List<String> = listOf("+", "-")
        private val Level_1: List<String> = listOf("(")

        fun getPriority(operation: String): Int {
            if(Level_5.contains(operation)) return 5
            if(Level_4.contains(operation)) return 4
            if(Level_3.contains(operation)) return 3
            if(Level_2.contains(operation)) return 2
            if(Level_1.contains(operation)) return 1
            return -1
        }
    }
}

//A function that returns the reverse polish notation of the expression
fun getRPN(expression: String): String?{
    //The variable that will contain the final expression
    var postfixString = ""
    try{
    //Deleting spaces in the expression
    val inputArray = expression.filter { !it.isWhitespace() }.toCharArray()
    //Stack for working with operations
    val operationStack: Stack<String> = Stack()
    var functionName = ""
    //Variable for checking the unarity of the operation
    var unary = true
    var i = 0
    while(i < inputArray.size) {
        //If the expression starts with a function or a non-unary operation
        if (inputArray[0] == ')' || inputArray[0] == '*' || inputArray[0] == '/' || inputArray[0] == '^') throw Exception()
        //Adding spaces to the output string
        if (i != 0 && inputArray[i].isDigit() && postfixString.isNotEmpty()) postfixString += " "
        //Reading a number
        while (inputArray[i].isDigit()) {
            postfixString += inputArray[i]; i++; if (!operationStack.empty()) if (operationStack.peek() == "(") unary = false
            if (i == inputArray.size) break
        }
        if (i == inputArray.size) break
        //Reading a function
        if (inputArray[i].isLetter()) {
            while (inputArray[i].isLetter()) {
                functionName += inputArray[i]; i++; if (i == inputArray.size) break
            }
            if(functionName != "sin" && functionName != "cos" && functionName != "tg" && functionName != "ctg" && functionName != "lg" && functionName != "ln")
                throw Exception()
            --i
        }
        if (i == inputArray.size) break
        //Just add an opening parenthesis to the stack
        if (inputArray[i] == '(') { operationStack.push(inputArray[i].toString()); ++i; continue }
        //If there is a closing parenthesis, then we read operations from the stack to the output
        //string until we meet an opening parenthesis
        else if (inputArray[i] == ')') {
            run loop@{
                operationStack.withIndex().reversed().forEach { operation ->
                    if(operation.value == "(") { operationStack.pop(); return@loop}
                    else postfixString += " " + operationStack.pop()
                }
            }
        }
        else {
            //If the stack is not empty, then we perform manipulations with the current operation
            //relative to the operations contained in the stack
            if (operationStack.isNotEmpty()) {
                run loop@{
                    operationStack.withIndex().reversed().forEach { operation ->
                        if ((inputArray[i] == '-' || inputArray[i] == '+' || functionName.isNotEmpty()) &&
                            (operationStack.peek() == "-" || operationStack.peek() == "+" || operationStack.peek() == "" || operationStack.peek() == "u-"))
                            unary = false
                        if (unary && (inputArray[i] == '-' || inputArray[i] == '+' || functionName.isNotEmpty()) && (operationStack.peek() == "(" || operationStack.peek() == "*" || operationStack.peek() == "/" || operationStack.peek() == "^")) {
                            if (inputArray[i] == '-') operationStack.push("u-")
                            else if (inputArray[i] == '+') operationStack.push("u+")
                            else operationStack.push(functionName)
                            unary = false
                            return@loop
                        }
                        if (OperationPriority.getPriority(operation.value) >= OperationPriority.getPriority(inputArray[i].toString())) { postfixString += " " + operationStack.pop() }
                        else { operationStack.push(inputArray[i].toString()); return@loop }
                        if (operationStack.empty()) { operationStack.push(inputArray[i].toString()); return@loop }
                    }
                }
            }
            //If the stack is empty, then either just insert the operation, or check the unarity
            //of the operation or the presence of a function, then push it
            else {
                if (inputArray[i] == '-' && postfixString.isEmpty()) operationStack.push("u-")
                else if (inputArray[i] == '+' && postfixString.isEmpty()) operationStack.push("u+")
                else if(functionName.isNotEmpty()) operationStack.push(functionName)
                else operationStack.push(inputArray[i].toString())
            }
        }
        ++i
        unary = true
        functionName = ""
    }
    //All that is left in the stack is added to the output string
    if(!operationStack.empty()) operationStack.withIndex().reversed().forEach { operation -> postfixString += " " + operation.value; operationStack.pop() }
    //If the ouputr string contains parentheses, then the expression was incorrect
    if(postfixString.contains("(") || postfixString.contains(")")) { throw Exception() }
    }
    catch(message: Exception){
        println("The parsing failed! Exception: $message")
        return null
    }
    return postfixString
}

//A function that calculates the value of an expression represented in
//reverse polish notation
fun calculate(input: String?): Float?{
    val result: Stack<String> = Stack()
    try {
        if(input == null)
            throw Exception()
        val parts = input.split(" ")
        var leftOperand = 0f
        var rightOperand: Float
        var operationResult = 0f
        for (part in parts) {
            if (part.toIntOrNull() != null) result.push(part)
            else {
                rightOperand = result.pop().toFloat()
                if (part != "u-" && part != "u+" && part != "sin" && part != "cos" && part != "tg" && part != "ctg" && part != "ln" && part != "lg")
                    leftOperand = result.pop().toFloat()
                when (part) {
                    "+" -> operationResult = leftOperand + rightOperand
                    "-" -> operationResult = leftOperand - rightOperand
                    "*" -> operationResult = leftOperand * rightOperand
                    "/" -> operationResult = leftOperand / rightOperand
                    "^" -> operationResult = leftOperand.pow(rightOperand)
                    "u-" -> operationResult = rightOperand * (-1)
                    "u+" -> operationResult = rightOperand
                    "sin" -> operationResult = sin(rightOperand)
                    "cos" -> operationResult = cos(rightOperand)
                    "tg" -> operationResult = tan(rightOperand)
                    "ctg" -> operationResult = atan(rightOperand)
                    "ln" -> operationResult = ln(rightOperand)
                    "lg" -> operationResult = log(rightOperand, 10f)
                }
                result.push(operationResult.toString())
            }
        }
    }
    catch(message: Exception){
        println("The parsing failed! Exception: $message")
        return null
    }
    return result.pop().toFloat()
}