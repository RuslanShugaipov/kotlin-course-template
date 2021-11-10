import alignment.*
//import calculator.*

fun main() {
    //Code for the alignment (lab 1)
    try {
        writeTextToFile("Align.txt", alignTextLeft("Something", 8))
    }
    catch (message: Exception){
        println(message)
    }

//    Code for the calculator (lab 2)
//    print("Result: ${calculate(getRPN("-sin(5)"))}")
}