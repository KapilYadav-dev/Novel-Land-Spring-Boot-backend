package `in`.kay.novland.data

import `in`.kay.novland.models.BookModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.lang.Nullable

interface BookRepository  : MongoRepository<BookModel, String> {

    @Query("{'isbn' : ?0}")
    @Nullable
    fun findByIsbn(isbn: String): BookModel

    @Nullable
    @Query("{'genre' : ?0}")
    fun findByGenre(genre: String): List<BookModel>

    @Query("{'isbn' : ?0}")
    @Nullable
    fun containBook(isbn: String?) : BookModel

    @Nullable
    @Query("{'author' : ?0}")
    fun findByAuthor(author: String): List<BookModel>
}
