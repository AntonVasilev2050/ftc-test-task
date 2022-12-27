package com.avvsoft2050.ftctesttask.data.api

import com.avvsoft2050.ftctesttask.data.model.Card
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{bin}")
    suspend fun getCard(
        @Path("bin") bin: String
    ): Card?
}