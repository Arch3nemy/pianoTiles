package com.alacrity.template.use_cases

import com.alacrity.template.entity.NumberWithFact

interface GetFactAboutNumberUseCase {

    suspend operator fun invoke(number: Int): NumberWithFact

}