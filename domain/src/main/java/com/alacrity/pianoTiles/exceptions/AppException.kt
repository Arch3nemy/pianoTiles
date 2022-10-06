package com.alacrity.pianoTiles.exceptions

open class AppException(message: String = "Undefined Error", exception: Throwable? = null): Exception(message, exception)