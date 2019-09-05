package com.saraelmoghazy.base.baselayer

/**
 * Created by Sara Elmoghazy
 */
data class APIException(
    override var message: String?,
    @ErrorType
    var errorType: Int
) : RuntimeException()