package `in`.kay.novland.services


import `in`.kay.novland.data.BookRepository
import `in`.kay.novland.models.BookModel
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class BookService(
    private val bookRepository: BookRepository
) {
    var pageable: Pageable = PageRequest.of(0, 100, Sort.by("title").ascending())

    fun getAllBooks(): List<BookModel> = bookRepository.findAll(pageable).get().collect(Collectors.toList())

    fun addBook(book: BookModel): BookModel = bookRepository.save(book)

    fun containsBook(isbn: String?): Boolean = bookRepository.containBook(isbn) != null

    fun getBookByIsbn(isbn: String): BookModel = bookRepository.findByIsbn(isbn)

    fun getBookByGenre(genre: String): List<BookModel> = bookRepository.findByGenre(genre)

    fun getBookByAuthor(author: String): List<BookModel> = bookRepository.findByAuthor(author)

    fun getAllGenre(): List<String> {
        val genreSet = mutableSetOf<String>()
        getAllBooks().forEach { it ->
            it.genre?.forEach {
                genreSet.add(it.capitalize())
            }
        }
        return genreSet.toList()
    }

    fun deleteAllBooks() = bookRepository.deleteAll()
}