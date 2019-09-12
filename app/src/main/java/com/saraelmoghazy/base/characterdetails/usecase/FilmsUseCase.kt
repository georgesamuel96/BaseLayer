package com.saraelmoghazy.base.characterdetails.usecase

import com.saraelmoghazy.base.baselayer.BaseUseCase
import com.saraelmoghazy.base.characterdetails.model.FilmsResponse
import com.saraelmoghazy.base.data.StarWarsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import retrofit2.Response

class FilmsUseCase(id: Int, private val repository: StarWarsRepository) :
    BaseUseCase<FilmsResponse>(id) {

    var filmIds = ArrayList<Int>()

    override suspend fun buildUseCase(): Response<FilmsResponse> {
        val result  =  joinAll(CoroutineScope(Dispatchers.IO).async {
            for (id in filmIds) {
                repository.getFilms(filmIds[id])
            }
        })
        result.



    }
}