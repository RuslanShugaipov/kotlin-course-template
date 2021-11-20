package alignment

import org.junit.Assert
import org.junit.Test
import java.io.FileNotFoundException

internal class AlignmentKtTest {

    @Test(expected = IllegalArgumentException::class)
    fun `checking for incorrect line width`() {
        alignTextLeft("Something", -1)
    }

    @Test
    fun `checking for empty text at the input`() {
        Assert.assertEquals("",alignTextLeft("", 20))
    }

    @Test(expected = FileNotFoundException::class)
    fun `checking for incorrect file name`() {
        alignTextLeft(readFileToLine("Wrong.txt"), 10)
    }

    @Test
    fun `checking alignment without complicated cases`() {
        Assert.assertEquals("C-3PO", alignTextLeft("  \n   C-3PO", 20))
    }

    @Test
    fun `checking if the punctuation mark doesn't fit in the line`() {
        Assert.assertEquals("xxxDS\nL. The", alignTextLeft("xxxDSL. The", 6))
        Assert.assertEquals("xxxDS\nL. Th\ne", alignTextLeft("xxxDSL. The", 5))
    }

    @Test
    fun `checking if the line width is 2 and 3`() {
        Assert.assertEquals("xx\nxD\nS\nL.\nTh\ne", alignTextLeft("xxxDSL. The", 2))
        Assert.assertEquals("xxx\nDS\nL. \nThe", alignTextLeft("xxxDSL. The", 3))
    }
}