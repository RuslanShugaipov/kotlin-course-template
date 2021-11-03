package calculator

import java.beans.Expression
import java.io.File
import java.util.*
import kotlin.math.pow

class OperationPriority {
    companion object {
        private val Level_5: List<String> = listOf("u-")
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

fun getRPN(expression: String): String{
    val inputArray = expression.filter { !it.isWhitespace() }.toCharArray()
    var postfixString = ""
    val operationStack: Stack<String> = Stack()
    var unary = true
    var i = 0
    while(i < inputArray.size) {
        if(i != 0 && inputArray[i].isDigit() && !postfixString.isEmpty()) postfixString += " "
        while(inputArray[i].isDigit()) {
            postfixString += inputArray[i]; i++; if(!operationStack.empty()) if(operationStack.peek() == "(") unary = false; if(i == inputArray.size) break}
        if(i == inputArray.size) break
        if(inputArray[i] == '(') {operationStack.push(inputArray[i].toString()); ++i; continue}
        else if(inputArray[i] == ')') {
            run loop@ {
                operationStack.withIndex().reversed().forEach { operation ->
                    if (operation.value == "(") { operationStack.pop(); return@loop }
                    else postfixString += " " + operationStack.pop()
                }
            }
        }
        else{
            if(!operationStack.empty()) {
                run loop@{
                    operationStack.withIndex().reversed().forEach { operation ->
                        if(inputArray[i] == '-' && (operationStack.peek() == "-" || operationStack.peek() == "+" || operationStack.peek() == "" || operationStack.peek() == "u-"))
                            unary = false
                        if(unary && inputArray[i] == '-' && (operationStack.peek() == "(" || operationStack.peek() == "*" || operationStack.peek() == "/"|| operationStack.peek() == "^")){
                            operationStack.push("u-")
                            unary = false
                            return@loop
                        }
                        if (OperationPriority.getPriority(operation.value) >= OperationPriority.getPriority(inputArray[i].toString()))
                            postfixString += " " + operationStack.pop()
                        else {
                            operationStack.push(inputArray[i].toString()); return@loop
                        }
                        if (operationStack.empty()) {operationStack.push(inputArray[i].toString()); return@loop}
                    }
                }
            }
            else{
                if(inputArray[i] == '-') operationStack.push("u-")
                else operationStack.push(inputArray[i].toString())
            }
        }
        ++i
        unary = true
    }
    if(!operationStack.empty()) operationStack.withIndex().reversed().forEach { operation -> postfixString += " " + operationStack.pop()}
    return postfixString
}

fun calculate(input: String): Float{
    val result: Stack<String> = Stack()
    val parts = input.split(" ")
    var leftOperand = 0f
    var rightOperand: Float
    var operationResult = 0f
    for(part in parts){
        if(part.toIntOrNull() != null) result.push(part)
        else{
            rightOperand = result.pop().toFloat()
            if(part != "u-" && part != "u+")
                leftOperand = result.pop().toFloat()
            when(part){
                "+" -> operationResult = leftOperand + rightOperand
                "-" -> operationResult = leftOperand - rightOperand
                "*" -> operationResult = leftOperand * rightOperand
                "/" -> operationResult = leftOperand / rightOperand
                "^" -> operationResult = leftOperand.pow(rightOperand)
                "u-" -> operationResult = rightOperand * (-1)
            }
            result.push(operationResult.toString())
        }
    }
    return result.pop().toFloat()
}