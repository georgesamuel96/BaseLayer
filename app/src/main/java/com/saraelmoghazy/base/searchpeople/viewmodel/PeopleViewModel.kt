package com.saraelmoghazy.base.searchpeople.viewmodel

import androidx.lifecycle.MutableLiveData
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseViewModel
import com.saraelmoghazy.base.baselayer.LoadingType
import com.saraelmoghazy.base.baselayer.UseCase
import com.saraelmoghazy.base.searchpeople.model.PeopleResponse
import com.saraelmoghazy.base.searchpeople.usecase.SearchPeopleUseCase

/**
 * Created by Sara Elmoghazy.
 */
class PeopleViewModel(useCase: SearchPeopleUseCase) : BaseViewModel() {

    val peopleLiveData = MutableLiveData<PeopleResponse>()
    @field:UseCase(R.id.PeopleUseCase)
    val peopleUseCase: SearchPeopleUseCase = useCase

    init {
        start()
    }

    override fun <M> onSuccess(response: M?) {
        if (response is PeopleResponse) {
            loadingIndicator.isLoading = false
            loadingIndicatorLiveData.value = loadingIndicator
            peopleLiveData.value = response
        }
    }

     fun searchPeople(query: String?) {
        loadingIndicator.loadingType = LoadingType.SHIMMER
        loadingIndicator.isLoading = true
        loadingIndicatorLiveData.value = loadingIndicator
        peopleUseCase.setParam(query)
        executeUseCase(peopleUseCase)
    }
}