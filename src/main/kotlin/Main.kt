import alignment.*
import calculator.*

fun main() {
//    println("Enter the line width: ")
//    var lineWidth: Int? = readLine()!!.toIntOrNull()
//    while(lineWidth == null){
//        println("Enter the correct value!")
//        lineWidth = readLine()!!.toIntOrNull()
//    }
//    print(alignText("TextToAlign5.txt", 0, Alignment.LEFT))


    val input1 = "1+(4-2)*6"
    val input2 = "( 3+ (4*2)/  ( ((1-5))^ 2))"
    val output = getRPN(input2)
    println(input2+"\n"+output)
    print("Result: ${calculate(output)}")
}