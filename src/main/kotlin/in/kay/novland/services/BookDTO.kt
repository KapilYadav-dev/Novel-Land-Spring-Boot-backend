package `in`.kay.novland.services

import javax.validation.constraints.NotBlank

data class AddBookDTO(
    @field:NotBlank val author: String? = null,
    val description: String? = null,
    val genre: List<String>? = null,
    @field:NotBlank val imageUrl: String? = null,
    @field:NotBlank(message = "Please enter ISBN") val isbn: String? = null,
    val pdfUrl: List<String>? = null,
    @field:NotBlank val title: String? = null
) {

}

