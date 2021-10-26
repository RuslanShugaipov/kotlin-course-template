package alignment

import org.junit.Test

import org.junit.Assert.*
import java.io.FileNotFoundException

class AlignmentKtTest {

    @Test(expected = IllegalArgumentException::class)
    fun `checking for incorrect line width`() {
        alignTextLeft("TextToAlign1.txt", -1)
    }

    @Test
    fun `checking for empty text at the input`() {
        assertEquals("", alignTextLeft("TextToAlign2.txt", 20))
    }

    @Test
    fun `checking alignment without complicated cases`() {
        assertEquals("C-3PO", alignTextLeft("TextToAlign3.txt", 20))
    }

    @Test
    fun `checking if the punctuation mark doesn't fit in the line`() {
        assertEquals("xxxDS\nL. The", alignTextLeft("TextToAlign4.txt", 6))
        assertEquals("xxxDS\nL. Th\ne", alignTextLeft("TextToAlign4.txt", 5))
    }

    @Test
    fun `checking if the line width is 2 and 3`() {
        assertEquals("xx\nxD\nS\nL.\nTh\ne", alignTextLeft("TextToAlign4.txt", 2))
        assertEquals("xxx\nDS\nL. \nThe", alignTextLeft("TextToAlign4.txt", 3))
    }

    @Test(expected = FileNotFoundException::class)
    fun `checking for incorrect file name`() {
        readFileToLines("Wrong.txt")
    }
}