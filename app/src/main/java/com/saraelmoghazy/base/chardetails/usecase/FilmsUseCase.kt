package com.saraelmoghazy.base.chardetails.usecase

import com.saraelmoghazy.base.baselayer.BaseUseCase
import com.saraelmoghazy.base.chardetails.model.Film
import com.saraelmoghazy.base.chardetails.model.FilmResponse
import com.saraelmoghazy.base.data.StarWarsRepository
import retrofit2.Response

class FilmsUseCase(id: Int, private val repository: StarWarsRepository) :
    BaseUseCase<FilmResponse>(id) {

    var filmIds = ArrayList<Int>()

    override suspend fun buildUseCase(): Response<FilmResponse> {
        var res: Response<FilmResponse>
        for (film in filmIds) {
            var x= repository.getFilms(film)
            if (!x.isSuccessful) {
                Response.error<>()
            } else {

            }

        }
    }
}