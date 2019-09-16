package com.saraelmoghazy.base.characterdetails.usecase

import com.saraelmoghazy.base.baselayer.BaseUseCase
import com.saraelmoghazy.base.characterdetails.model.FilmResponse
import com.saraelmoghazy.base.characterdetails.model.FilmsResponse
import com.saraelmoghazy.base.data.StarWarsRepository
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.ConcurrentLinkedQueue

class FilmsUseCase(id: Int, private val repository: StarWarsRepository) :
    BaseUseCase<FilmsResponse>(id) {

    var filmIds = ArrayList<Int>()

    override suspend fun buildUseCase(): Response<FilmsResponse> {
        val lstOfFilms = ArrayList<FilmResponse>()
        val lstOfReturnData = ConcurrentLinkedQueue<Response<FilmResponse>>()
        runBlocking {
            processRequests(lstOfReturnData)
        }
        lstOfReturnData.forEach {
            if (it.isSuccessful) {
                lstOfFilms.add(it.body()!!)
            } else
                return Response.error(400, it.errorBody())

        }
        var filmsResponse = FilmsResponse()
        filmsResponse.films.addAll(lstOfFilms)
        return Response.success(filmsResponse)
    }


    private suspend fun processRequests(lstOfReturnData: ConcurrentLinkedQueue<Response<FilmResponse>>) {
        for (id in filmIds) {
            lstOfReturnData.add(repository.getFilms(filmIds[id]))
        }
    }
}
