package com.saraelmoghazy.base.data.remote

import com.saraelmoghazy.base.data.StarWarsRepository
import com.saraelmoghazy.base.searchpeople.model.PeopleResponse
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by Sara Elmoghazy.
 */
class StarWarsRemoteDataSource(retrofit: Retrofit) :
    StarWarsRepository {
    private val serviceApi: ServiceApi = retrofit.create(ServiceApi::class.java)

    override suspend fun searchPeople(search: String?): Response<PeopleResponse> {
        return serviceApi.searchPeople(search)
    }
}