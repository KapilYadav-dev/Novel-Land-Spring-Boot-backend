package `in`.kay.novland.services

data class ResponseDTO(var isSuccess: Boolean, var msg: String, var statusCode: Int = 200) {
}
