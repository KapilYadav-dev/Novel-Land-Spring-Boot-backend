package `in`.kay.novland.models

import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "BooksCollection")
data class BookModel(
    var isbn: String? = null,
    var author: String? = "by Unknown",
    var description: String? = "",
    var genre: List<String>? = null,
    var imageUrl: String? = null,
    var pdfUrl: List<String>? = null,
    var report: Int? = 0,
    var title: String? = null,
    var rating: Float? = 5.0f,
    var comments: List<String>? = emptyList()
) {

}