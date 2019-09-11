package com.saraelmoghazy.base.searchpeople.viewmodel

import androidx.lifecycle.MutableLiveData
import com.saraelmoghazy.base.R
import com.saraelmoghazy.base.baselayer.BaseViewModel
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
            peopleLiveData.value = response
        }
    }

    fun searchPeople(query: String?) {
        peopleUseCase.setParam(query)
        executeUseCase(peopleUseCase)
    }
}