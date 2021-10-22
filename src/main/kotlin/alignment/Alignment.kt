package alignment

import java.io.File

//enum class with alignment types
enum class Alignment {
    LEFT
}

//function calling the required alignment type
fun alignText(fileName: String, lineWidth: Int, alignment: Alignment) {
    try {
        when (alignment) {
            Alignment.LEFT -> {
                alignTextLeft(fileName, lineWidth)
                print("Aligned text in the file AlignedText.txt.")
            }
        }
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

    for(line in lineList) {
        //split a string from the source text into separate parts (words)
        val parts = line.split(Regex("\\s+"))

        for(part in parts) {
            if(currentLineWidth > 0) {
                var isLastSpace = false
                if(currentLineWidth < lineWidth) {
                    writer.write(" ")
                    if (currentLineWidth == lineWidth - 1)
                        isLastSpace = true
                    ++currentLineWidth
                }
                if(currentLineWidth == lineWidth) {
                    writer.newLine()
                    currentLineWidth = 0
                    if(!isLastSpace) {
                        writer.write(" ")
                        currentLineWidth = 1
                    }
                }

                if(currentLineWidth + part.length > lineWidth) {
                    writer.write(part.substring(0, lineWidth - currentLineWidth))
                    writer.newLine()
                    var index = lineWidth - currentLineWidth
                    currentLineWidth = 0
                    while(index < part.length) {
                        if(part.length - index <= lineWidth) {
                            writer.write(part.substring(index))
                            currentLineWidth = part.length - index
                            break
                        }
                        else {
                            writer.write((part.substring(index, index + lineWidth)))
                            writer.write("\n")
                            index += lineWidth
                        }
                    }
                    continue
                }
            }
            else if(part.length >= lineWidth) {
                //if the length of the word is equal to the length of the string,
                //then just insert it and move to the next part
                if(part.length == lineWidth) {
                    writer.write(part)
                    writer.newLine()
                    continue
                }

                var index = 0
                while(index + lineWidth <= part.length) {
                    writer.write(part.substring(index, index + lineWidth))
                    writer.newLine()
                    index += lineWidth
                    currentLineWidth = 0
                }
                if(index != part.length) {
                    writer.write(part.substringAfter(part[index - 1]))
                    currentLineWidth = part.substringAfter(part[index - 1]).length
                }
                continue
            }
            //if the current length of the string equals zero and the word fits in the string
            currentLineWidth += part.length
            writer.write(part)
        }
    }
    writer.close()
}

//function that reads lines of text into a list of strings
fun readFileToLines(fileName: String): MutableList<String> {
    val lineList = mutableListOf<String>()
    File(fileName).bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
    return lineList
}