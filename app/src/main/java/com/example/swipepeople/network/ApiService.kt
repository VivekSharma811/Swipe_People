package com.example.swipepeople.network

import com.example.swipepeople.data.model.PeopleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/0.4?randomapi")
    fun getPeople(): Single<PeopleResponse>

}