package com.alacrity.template.repository

import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
) : Repository {



}