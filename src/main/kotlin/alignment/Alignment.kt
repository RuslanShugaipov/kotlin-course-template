package alignment

import java.io.File

//enum class with alignment types
enum class Alignment {
    LEFT,
//    RIGHT,
//    CENTER,
//    JUSTIFY
}

//function calling the required alignment type
fun alignText(fileName: String, lineWidth: Int, alignment: Alignment) {
    try {
            when (alignment) {
                Alignment.LEFT -> { alignTextLeft(fileName, lineWidth) }
//                Alignment.RIGHT -> { alignTextRight(fileName, lineWidth) }
//                Alignment.CENTER -> { alignTextCenter(fileName, lineWidth) }
//                Alignment.JUSTIFY -> { alignTextJustify(fileName, lineWidth) }
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
    val punctuationMarks = listOf('.', ',', '?', '!', ';', ':',
        ')', ']', '}', '\'', '\"', '>')
    var isMark = false

    for(line in lineList) {
        //split a string from the source text into separate parts (words)
        val parts = line.split(Regex("\\s+"))
        for(part in parts) {
            if(part == "") continue
            //construction for adding spaces between words
            if((0 < currentLineWidth) && (currentLineWidth < lineWidth)) {
                writer.write(" ")
                ++currentLineWidth
            }
            //if the length of the string is too small, then in the loop
            //we divide the word and insert it until the remaining length
            //of the word becomes less than the width of the line. At the
            //end, we add what's left
            if(part.length + currentLineWidth >= lineWidth){
                var index = 0
                while(part.length - index >= 1) {
                    if(part.length - index < lineWidth - currentLineWidth) {
                        writer.write(part.substring(index, part.length))
                        currentLineWidth = part.substring(index, part.length).length
                        break
                    }
                    else {
                        //if the punctuation mark doesn't fit in the line
                        punctuationMarks.forEach {
                            if(it == part[part.length - 1])
                                isMark = true
                        }
                        if(isMark  && (part.length - index - (lineWidth - currentLineWidth) == 1 && lineWidth != 1)){
                            ++currentLineWidth
                            writer.write(part.substring(index, index + (lineWidth - currentLineWidth)))
                            writer.newLine()
                            writer.write(part.substring(part.length - 2, part.length))
                            currentLineWidth = 2
                            isMark = false
                            break
                        }
                        else {
                            writer.write(part.substring(index, index + (lineWidth - currentLineWidth)))
                            writer.newLine()
                            index += lineWidth - currentLineWidth
                            currentLineWidth = 0
                            isMark = false
                        }
                    }
                }
                continue
            }
            //if the word fits into the line without any problems
            writer.write(part)
            currentLineWidth += part.length
        }
    }
    writer.close()
}

////function that aligns a text to the right
//fun alignTextRight(fileName: String, lineWidth: Int){
//}
//
////function that aligns a text to the center
//fun alignTextCenter(fileName: String, lineWidth: Int){
//}
//
////function that aligns a text to the width (justify)
//fun alignTextJustify(fileName: String, lineWidth: Int){
//}

//function that reads lines of text into a list of strings
fun readFileToLines(fileName: String): MutableList<String> {
    val lineList = mutableListOf<String>()
    File(fileName).bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
    return lineList
}