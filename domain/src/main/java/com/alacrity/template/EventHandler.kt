package com.alacrity.template

interface EventHandler<T> {
    fun obtainEvent(event: T)
}