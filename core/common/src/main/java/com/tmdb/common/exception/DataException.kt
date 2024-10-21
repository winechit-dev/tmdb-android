package com.tmdb.common.exception

sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    data object Network : DataException("Unable to connect. Please check connection.")

    data class DataNotFound(
        override val message: String
    ) : DataException(message)

    data class IOOperation(
        override val message: String
    ) : DataException(message)

    data class Api(
        override val message: String,
        val title: String = "",
        val errorCode: Int = -1
    ) : DataException(message)
}
