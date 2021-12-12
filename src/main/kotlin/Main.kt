//import alignment.*
//import calculator.*
//import shape.*
//import matrix.*
import library.*

fun main() {
    try {
//        Code for the library (lab 5)
        val newLibrary = LibraryServiceImpl()
        val book1 = Book("Harry Potter and the Philosopher's Stone", listOf(Author("Joanne", "Kathleen", "Rowling")), Genre.Fantasy, Year(1997))
        val book2 = Book("The fellowship of the ring", listOf(Author("John", "Ronald", "Tolkien")), Genre.Fantasy, Year(1954))
        val book3 = Book("1984", listOf(Author("Orwell", "George", "")), Genre.Classic, Year(1949))
        val book4 = Book("Neuromancer", listOf(Author("Gibson", "William", "Ford")), Genre.Cyberpunk, Year(1984))
        newLibrary.addBook(book1)
        newLibrary.addBook(book2)
        newLibrary.addBook(book3)
        newLibrary.addBook(book4)
        val findBook1 = newLibrary.findBooks("Harry", year = Year(1997))
        println("\nFilter: Year: 1997, Title includes: Harry (Default genre is Classic)\nFound books:")
        if(findBook1.isEmpty())
            println("\tNone")
        else
            findBook1.forEach{book ->
            println(newLibrary.bookInfo(book))
            }
        val findBook2 = newLibrary.findBooks("The")
        println("\nFilter: Title includes: The (Default genre is Classic)\nFound books:")
        if(findBook2.isEmpty())
            println("\tNone")
        else
            findBook2.forEach{book ->
            println(newLibrary.bookInfo(book))
            }
        println(newLibrary.getBookStatus(book1)::class.java.simpleName)
        val user1 = User("Jack", "London")
        val user2 = User("Ivan", "Drozd")
        newLibrary.registerUser(user1)
        newLibrary.registerUser(user2)
        newLibrary.registerUser(user1)
        newLibrary.takeBook(user1, book1)
        newLibrary.takeBook(user1, book2)
        newLibrary.takeBook(user1, book3)
        newLibrary.takeBook(user2, book4)
        newLibrary.unregisterUser(user1)
    } catch (message: Exception) {
        println(message)
    }
}