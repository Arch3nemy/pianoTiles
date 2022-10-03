package com.alacrity.template.use_cases

import com.alacrity.template.repository.Repository
import javax.inject.Inject

class GetFactAboutNumberUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetFactAboutNumberUseCase {

    override suspend fun invoke(number: Int) = repository.getFact(number)

}