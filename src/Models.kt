package ca.rjreid

enum class ResponseStatus {
    SUCCESS,
    ERROR
}

data class Response(val status: ResponseStatus)