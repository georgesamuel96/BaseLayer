package com.saraelmoghazy.base.baselayer

import retrofit2.Response


/**
 * Abstract class for a Use Case
 * This class represents an execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 */
abstract class BaseUseCase<M> {

    val id: Int

    constructor(id: Int) {
        this.id = id
    }

    protected abstract suspend fun buildUseCase(): Response<M>

    suspend fun execute(): Response<M> {
          return  buildUseCase()

    }
}