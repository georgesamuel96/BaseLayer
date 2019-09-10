package com.saraelmoghazy.base.viewmodel

import androidx.lifecycle.MutableLiveData
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseViewModel
import com.saraelmoghazy.base.baselayer.LoadingType
import com.saraelmoghazy.base.baselayer.UseCase
import com.saraelmoghazy.base.model.PeopleResponse
import com.saraelmoghazy.base.usecase.StarWarUseCase

/**
 * Created by Sara Elmoghazy.
 */
class StarWarViewModel(useCase: StarWarUseCase) : BaseViewModel() {

    val peopleLiveData = MutableLiveData<PeopleResponse>()
    @field:UseCase(R.id.PeopleUseCase)
    val peopleUseCase: StarWarUseCase = useCase

    init {
        start()
        searchPeople()
    }

    override fun <M> onSuccess(response: M?) {
        if (response is PeopleResponse) {
            loadingIndicator.isLoading = false
            loadingIndicatorLiveData.value = loadingIndicator
            peopleLiveData.value = response
        }
    }

    private fun searchPeople() {
        loadingIndicator.loadingType = LoadingType.SHIMMER
        loadingIndicator.isLoading = true
        loadingIndicatorLiveData.value = loadingIndicator
        executeUseCase(peopleUseCase)
    }
}