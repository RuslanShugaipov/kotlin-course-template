package calculator

import java.beans.Expression
import java.io.File
import java.util.*
import kotlin.math.pow

class OperationPriority {
    companion object {
        private val Level_4: List<Char> = listOf('^')
        private val Level_3: List<Char> = listOf('*', '/')
        private val Level_2: List<Char> = listOf('+', '-')
        private val Level_1: List<Char> = listOf('(')

        fun getPriority(operation: Char): Int {
            if(Level_4.contains(operation)) return 4
            if(Level_3.contains(operation)) return 3
            if(Level_2.contains(operation)) return 2
            if(Level_1.contains(operation)) return 1
            return 0
        }
    }
}

fun getRPN(expression: String): String{
    var inputArray = expression.filter { !it.isWhitespace() }.toCharArray()
    var postfixString = ""
    var operationStack: Stack<Char> = Stack()
    var i = 0
    while(i < inputArray.size)
    {
        if(i != 0 && inputArray[i].isDigit() && !postfixString.isEmpty()) {postfixString += " "}
        while(inputArray[i].isDigit()) {postfixString += inputArray[i]; i++; if(i == inputArray.size) break}
        if(i == inputArray.size) break
        if(inputArray[i] == '(') {operationStack.push(inputArray[i]); ++i; continue}
        if(OperationPriority.getPriority(inputArray[i]) > 0) {
            if(!operationStack.empty()) {
                run loop@{
                    operationStack.withIndex().reversed().forEach { operation ->
                        if (OperationPriority.getPriority(operation.value) >= OperationPriority.getPriority(inputArray[i])) postfixString += " " + operationStack.pop()
                        else {operationStack.push(inputArray[i]); return@loop}
                        if (operationStack.empty()) return@loop
                    }
                }
            }
            if(operationStack.empty()) operationStack.push(inputArray[i])
        }
        else if(inputArray[i] == ')') {
            run loop@ {
                operationStack.withIndex().reversed().forEach { operation ->
                    if (operation.value == '(') { operationStack.pop(); return@loop }
                    else postfixString += " " + operationStack.pop()
                }
            }
        }
        ++i
    }
    if(!operationStack!!.empty()) operationStack.withIndex().reversed().forEach { operation -> postfixString += " " + operationStack.pop()}
    return postfixString
}

fun calculate(input: String): Float{
    var result: Stack<String> = Stack()
    val parts = input.split(" ")
    var leftOperand: Float
    var rightOperand: Float
    var operationResult = 0f
    for(part in parts){
        if(part.toIntOrNull() != null) result.push(part)
        else{
            rightOperand = result.pop().toFloat()
            leftOperand = result.pop().toFloat()
            when(part){
                "+" -> operationResult = leftOperand + rightOperand
                "-" -> operationResult = leftOperand - rightOperand
                "*" -> operationResult = leftOperand * rightOperand
                "/" -> operationResult = leftOperand / rightOperand
                "^" -> operationResult = leftOperand.pow(rightOperand)
            }
            result.push(operationResult.toString())
        }
    }
    return result.pop().toFloat()
}