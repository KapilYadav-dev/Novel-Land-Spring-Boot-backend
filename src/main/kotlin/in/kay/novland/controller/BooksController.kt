package `in`.kay.novland.controller

import `in`.kay.novland.models.BookModel
import `in`.kay.novland.services.AddBookDTO
import `in`.kay.novland.services.BookService
import `in`.kay.novland.services.ResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1")
class BooksController(
    private val bookService: BookService
) {
    @RequestMapping("/book")
    fun addBook(@Valid @RequestBody bookModel: AddBookDTO): ResponseEntity<*> {
        if (bookService.containsBook(bookModel.isbn)) {
            return ResponseEntity(ResponseDTO(false, "Book already exists", 409), HttpStatus.CONFLICT)
        }
        bookService.addBook(
            BookModel(
                title = bookModel.title,
                isbn = bookModel.isbn,
                genre = bookModel.genre?.map { it -> it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } },
                author = bookModel.author?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                imageUrl = bookModel.imageUrl,
                pdfUrl = bookModel.pdfUrl,
                description = bookModel.description.toString(),
                rating = 5.0f,
                comments = emptyList()
            )
        )
        return ResponseEntity(ResponseDTO(true, "Added Books", 200), HttpStatus.OK)
    }

    @GetMapping("/books")
    fun getBooks(): ResponseEntity<List<BookModel>> {
        val books = bookService.getAllBooks()
        println(books.toString())
        return ResponseEntity(books, HttpStatus.OK)
    }

    @GetMapping("/books/isbn/{isbn}")
    fun getBookByIsbn(@PathVariable("isbn") isbn: String): ResponseEntity<Any> {
        val book = bookService.getBookByIsbn(isbn)
            ?: return ResponseEntity(ResponseDTO(false, "There's is no book with isbn $isbn", 404), HttpStatus.NOT_FOUND)
        return ResponseEntity(book, HttpStatus.OK)
    }

    @GetMapping("/books/genre/{genre}")
    fun getBookByGenre(@PathVariable("genre") genre: String): ResponseEntity<Any> {
        val book = bookService.getBookByGenre(genre.capitalize())
        if (book.isEmpty()) {
            return ResponseEntity(ResponseDTO(false, "There's is no book with genre $genre", 404), HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(book, HttpStatus.OK)
    }

    @GetMapping("/books/author/{author}")
    fun getBookByAuthor(@PathVariable("author") author: String): ResponseEntity<Any> {
        val book = bookService.getBookByAuthor(author)
        if (book.isEmpty()) {
            return ResponseEntity.badRequest().body(ResponseDTO(false, "There's is no book with author $author", 404))
        }
        return ResponseEntity(book, HttpStatus.OK)
    }

    @GetMapping("/getAllGenres")
    fun getGenre(): ResponseEntity<List<String>> {
        val genres = bookService.getAllGenre()
        return ResponseEntity(genres, HttpStatus.OK)
    }

    @GetMapping("/deleteAll")
    fun deleteAllBooks(): ResponseEntity<ResponseDTO> {
        bookService.deleteAllBooks()
        return ResponseEntity(ResponseDTO(true, "Deleted all books", 200), HttpStatus.OK)
    }
}