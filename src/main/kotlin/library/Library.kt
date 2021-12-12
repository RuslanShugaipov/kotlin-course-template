package library

data class Book(val title: String, val authors: List<Author>, val genre: Genre, val year: Year)

data class Author(val surname: String, val firstName: String, val patronymic: String)

data class User(val name: String, val surname: String)

data class Year(val year: Int)

enum class Genre {
    Fantasy,
    Cyberpunk,
    Classic,
    Western
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun sendForRestoration(user: User, book: Book)
    fun getFromRestoration(book: Book)
    fun takeBook(user: User, book: Book)
    fun returnBook(user: User, book: Book)

    fun bookInfo(book: Book): String
}

class LibraryServiceImpl : LibraryService {
    private val users = mutableListOf<User>()
    private val userBooks = mutableMapOf<User, MutableList<Book>>()
    private val books = mutableMapOf<Book, Status>()

    fun findBooks(substring: String, author: Author = Author("", "", ""),
                  year: Year = Year(0), genre: Genre = Genre.Classic): List<Book>{
        val foundBooks = arrayListOf<Book>()
        for((key, _) in books) {
            if(key.title.lowercase().contains(substring.lowercase()) || key.authors.contains(author) || key.year == year || key.genre == genre) {
                foundBooks.add(key)
            }
        }
        return foundBooks
    }

    override fun getAllBooks(): List<Book> {
        val allBooks = arrayListOf<Book>()
        for((key, _) in books) {
            allBooks.add(key)
        }
        return allBooks
    }

    override fun getAllAvailableBooks(): List<Book> {
        val allAvailableBooks = arrayListOf<Book>()
        for((key, value) in books) {
            if(value == Status.Available) {
                allAvailableBooks.add(key)
            }
        }
        return allAvailableBooks
    }

    override fun getBookStatus(book: Book): Status {
        return books.getOrDefault(book, Status.ComingSoon)
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        val allBookStatuses = mutableMapOf<Book, Status>()
        for((key, value) in books) {
            allBookStatuses[key] = value
        }
        return allBookStatuses
    }

    override fun setBookStatus(book: Book, status: Status) {
        books[book] = status
    }

    override fun addBook(book: Book) {
        if (books.containsKey(book)){
            println("This book is already in the library.")
            return
        }
        setBookStatus(book, Status.Available)
        println("The book \"${book.title}\" has been successfully added.")
    }

    override fun sendForRestoration(user: User, book: Book) {
        if(userBooks[user]!!.contains(book)){
            println("The book is in the user's possession.")
            return
        }
        books[book] = Status.Restoration
    }

    override fun getFromRestoration(book: Book) {
        setBookStatus(book, Status.Available)
    }

    override fun registerUser(user: User) {
        if(users.contains(user)){
            println("The user ${user.name} ${user.surname} is already registered.")
            return
        }
        users.add(user)
        userBooks[user] = mutableListOf()
        println("The user ${user.name} ${user.surname} has been successfully registered.")
    }

    override fun unregisterUser(user: User) {
        if(!users.contains(user)){
            println("The user is not registered.")
            return
        }
        var bookIndex = userBooks[user]!!.size - 1
        while(userBooks[user]!!.size != 0){
            returnBook(user, userBooks[user]!![bookIndex--])
        }
        userBooks.remove(user)
        users.remove(user)
        println("The user ${user.name} ${user.surname} has been successfully unregistered.")
    }

    override fun takeBook(user: User, book: Book) {
        if(userBooks[user]?.size == 3){
            println("The limit on issued books had been exceeded.")
            return
        }
        if(!users.contains(user)){
            println("The issue of the book is possible only for registered users.")
            return
        }
        if(getBookStatus(book) != Status.Available) {
            println("The book is not available.")
            return
        }
        userBooks[user]?.add(book)
        setBookStatus(book, Status.UsedBy(user))
    }

    override fun returnBook(user: User, book: Book) {
        userBooks[user]?.remove(book)
        setBookStatus(book, Status.Available)
    }

    override fun bookInfo(book: Book) : String {
        var info = ""
        info += "\tTitle: ${book.title}, author: "
        book.authors.forEach{author ->
            info += if(author.patronymic.isNotEmpty())
                "${author.surname} ${author.firstName.substring(0,1)}.${author.patronymic.substring(0,1)}., "
            else
                "${author.surname} ${author.firstName.substring(0,1)}., "
        }
        info += "genre: ${book.genre}, year: ${book.year.year}"
        return info
    }
}