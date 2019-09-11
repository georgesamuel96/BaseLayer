package com.saraelmoghazy.base.data.remote

import com.saraelmoghazy.base.chardetails.model.PlanetsResponse
import com.saraelmoghazy.base.chardetails.model.SpeciesResponse
import com.saraelmoghazy.base.data.StarWarsRepository
import com.saraelmoghazy.base.searchpeople.model.PeopleResponse
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by Sara Elmoghazy.
 */
class StarWarsRemoteDataSource(retrofit: Retrofit) :
    StarWarsRepository {
    override suspend fun getSpecies(id: Int): Response<SpeciesResponse> {
        return serviceApi.getSpecies(id)
    }

    override suspend fun getPlanets(id: Int): Response<PlanetsResponse> {
        return serviceApi.getPlanets(id)
    }

    private val serviceApi: ServiceApi = retrofit.create(ServiceApi::class.java)

    override suspend fun searchPeople(search: String?): Response<PeopleResponse> {
        return serviceApi.searchPeople(search)
    }
}