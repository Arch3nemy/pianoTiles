package com.alacrity.pianoTiles

interface EventHandler<T> {
    fun obtainEvent(event: T)
}