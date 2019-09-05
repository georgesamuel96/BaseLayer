package com.saraelmoghazy.base.data

import com.saraelmoghazy.base.model.PeopleResponse
import retrofit2.Response

/**
 * Created by Sara Elmoghazy.
 */
interface StarWarsRepository {

    suspend fun searchPeople(search: String): Response<PeopleResponse>
}