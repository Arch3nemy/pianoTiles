package com.alacrity.template.exceptions

class NoDataFromResponseException(message: String = "Undefined", exception: Throwable? = null) : TemplateException(message, exception)