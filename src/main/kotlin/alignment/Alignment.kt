package alignment

import java.io.File

//enum class with alignment types
enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}

//function calling the required alignment type
fun alignText(fileName: String, lineWidth: Int, alignment: Alignment) {
    try {
        when (alignment) {
            Alignment.LEFT -> {
                alignTextLeft(fileName, lineWidth)
            }
            Alignment.RIGHT -> {
                alignTextRight(fileName, lineWidth)
            }
            Alignment.CENTER -> {
                alignTextCenter(fileName, lineWidth)
            }
            Alignment.JUSTIFY -> {
                alignTextJustify(fileName, lineWidth)
            }
        }
        print("\nAligned text in the file AlignedText.txt.")
    }
    catch (message: Exception){
        println(message)
    }
}

//function that aligns a text to the left
fun alignTextLeft(fileName: String, lineWidth: Int) {
    if(lineWidth <= 0)
        throw IllegalArgumentException("\u001B[31m" + "Invalid line width value!")
    //creating mutable list of strings
    val lineList = readFileToLines(fileName)

    val writer = File("AlignedText.txt").bufferedWriter()
    var currentLineWidth = 0
    val punctuationMarks = listOf(".", ",", "?", "!", ";", ":", "...",
        ")", "]", "}", "'", "\"", ">")

    for(line in lineList) {
        //split a string from the source text into separate parts (words)
        val parts = line.split(Regex("\\s+"))
        for(part in parts) {
            if(part == "")
                continue
            if(part.length + currentLineWidth >= lineWidth){
                var index = 0
                while(part.length - index > 0) {
                    if(part.length - index < lineWidth - currentLineWidth) {
                        writer.write(part.substring(index, part.length))
                        writer.write(" ")
                        currentLineWidth = part.substring(index, part.length).length + 1
                        if(currentLineWidth == lineWidth) {
                            writer.newLine()
                            currentLineWidth = 0
                        }
                        break
                    }
                    else {
                        writer.write(part.substring(index, index + (lineWidth - currentLineWidth)))
                        writer.newLine()
                        index += lineWidth - currentLineWidth
                        currentLineWidth = 0
                    }
                }
                continue
            }
            writer.write(part)
            currentLineWidth += part.length
            writer.write(" ")
            ++currentLineWidth
            if(currentLineWidth == lineWidth) {
                writer.newLine()
                currentLineWidth = 0
            }
        }
    }
    writer.close()
}

//function that aligns a text to the right
fun alignTextRight(fileName: String, lineWidth: Int){
}

//function that aligns a text to the center
fun alignTextCenter(fileName: String, lineWidth: Int){
}

//function that aligns a text to the width (justify)
fun alignTextJustify(fileName: String, lineWidth: Int){
}

//function that reads lines of text into a list of strings
fun readFileToLines(fileName: String): MutableList<String> {
    val lineList = mutableListOf<String>()
    File(fileName).bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
    return lineList
}