package com.saraelmoghazy.base.data.remote

import com.saraelmoghazy.base.characterdetails.model.FilmResponse
import com.saraelmoghazy.base.characterdetails.model.PlanetsResponse
import com.saraelmoghazy.base.characterdetails.model.SpeciesResponse
import com.saraelmoghazy.base.searchpeople.model.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Sara Elmoghazy.
 */
interface ServiceApi {

    @GET("people/")
    suspend fun searchPeople(
        @Query("search") key: String?
    ): Response<PeopleResponse>


    @GET("planets/{id}")
    suspend fun getPlanets(
        @Path("id") key: Int?
    ): Response<PlanetsResponse>

    @GET("species/{id}")
    suspend fun getSpecies(
        @Path("id") key: Int?
    ): Response<SpeciesResponse>


    @GET("films/{id}")
    suspend fun getFilms(
        @Path("id") key: Int?
    ): Response<FilmResponse>

}
