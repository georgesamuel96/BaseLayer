package com.saraelmoghazy.base.data.remote

import com.saraelmoghazy.base.model.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sara Elmoghazy.
 */
interface ServiceApi {

    @GET("people/")
    suspend  fun searchPeople(
        @Query("search") key: String
    ): Response<PeopleResponse>
}
