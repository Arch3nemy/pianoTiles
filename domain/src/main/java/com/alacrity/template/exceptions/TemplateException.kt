package com.alacrity.template.exceptions

open class TemplateException(message: String = "Undefined Error", exception: Throwable? = null): Exception(message, exception)