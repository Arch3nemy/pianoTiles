package com.alacrity.template.repository

import com.alacrity.template.entity.NumberWithFact

interface Repository {

    suspend fun getFact(number: Int): NumberWithFact

}