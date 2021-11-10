package alignment

import java.io.File

//function that aligns a text to the left
fun alignTextLeft(text: String, lineWidth: Int): String {
    if (lineWidth <= 0)
        throw IllegalArgumentException("Invalid line width value!")
    if(text == "")
        throw Exception("There is no text to align!")

    val lines = text.split('\n')
    var currentLineWidth = 0
    val punctuationMarks = listOf(
        '.', ',', '?', '!', ';', ':',
        ')', ']', '}', '\'', '\"', '>'
    )
    var isMark = false
    var alignedText = ""

    for (line in lines) {
        //split a string from the source text into separate parts (words)
        val parts = line.split(Regex("\\s+"))
        for (part in parts) {
            if (part == "") continue
            //construction for adding spaces between words
            if ((0 < currentLineWidth) && (currentLineWidth < lineWidth)) {
                alignedText += " "
                ++currentLineWidth
            }
            //if the length of the string is too small, then in the loop
            //we divide the word and insert it until the remaining length
            //of the word becomes less than the width of the line. At the
            //end, we add what's left
            if (part.length + currentLineWidth > lineWidth) {
                var index = 0
                while (part.length - index >= 1) {
                    if (part.length - index <= lineWidth - currentLineWidth) {
                        alignedText += part.substring(index, part.length)
                        currentLineWidth = part.substring(index, part.length).length
                        break
                    } else {
                        //if the punctuation mark doesn't fit in the line
                        punctuationMarks.forEach {
                            if (it == part[part.length - 1])
                                isMark = true
                        }
                        if (isMark && (part.length - index - (lineWidth - currentLineWidth) == 1 && lineWidth != 1)) {
                            ++currentLineWidth
                            alignedText += part.substring(index, index + (lineWidth - currentLineWidth)) +
                                    "\n" + part.substring(part.length - 2, part.length)
                            currentLineWidth = 2
                            isMark = false
                            break
                        } else {
                            alignedText += part.substring(index, index + (lineWidth - currentLineWidth)) + "\n"
                            index += lineWidth - currentLineWidth
                            currentLineWidth = 0
                            isMark = false
                        }
                    }
                }
                continue
            }
            //if the word fits into the line without any problems
            alignedText += part
            currentLineWidth += part.length
        }
    }
    return alignedText
}

//function that reads lines of text into a list of strings
fun readFileToLine(fileName: String): String {
    return File(fileName).readText()
}

//function that writes aligned text to file
fun writeTextToFile(fileName: String, Text: String) {
    File(fileName).writeText(Text)
}