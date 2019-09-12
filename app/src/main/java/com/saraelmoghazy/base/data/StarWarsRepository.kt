package com.saraelmoghazy.base.data

import com.saraelmoghazy.base.characterdetails.model.FilmResponse
import com.saraelmoghazy.base.characterdetails.model.PlanetsResponse
import com.saraelmoghazy.base.characterdetails.model.SpeciesResponse
import com.saraelmoghazy.base.searchpeople.model.PeopleResponse
import retrofit2.Response

/**
 * Created by Sara Elmoghazy.
 */
interface StarWarsRepository {

    suspend fun searchPeople(search: String?): Response<PeopleResponse>

    suspend fun getPlanets(id: Int): Response<PlanetsResponse>

    suspend fun getSpecies(id: Int): Response<SpeciesResponse>

    suspend fun getFilms(id: Int): Response<FilmResponse>

}