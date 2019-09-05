package com.saraelmoghazy.base.baselayer

import android.util.SparseArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.*


/**
 * Created by Sara Elmoghazy.
 */
open abstract class BaseViewModel : ViewModel() {

    private var useCases: SparseArray<BaseUseCase<*>> = SparseArray()
    private val failedUseCasesList = ArrayList<BaseUseCase<*>>()
    val loadingIndicatorLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<APIException>()

    companion object {
        const val ERROR = "error"
        const val MESSAGE = "message"
    }

    protected fun start() {
        fillAllUseCasesList()
    }

    /**
     * Read all [.useCases] annotated with useCase annotation and add them to local list
     */
    private fun fillAllUseCasesList() {
        try {
            retrieveDeclaredUSeCases()
        } catch (e: IllegalAccessException) {
        }
    }

    /**
     * Read all useCases annotated with useCase annotation.
     */
    private fun retrieveDeclaredUSeCases() {
        val declaredFields = this::class.java.declaredFields
        for (field in declaredFields) {
            for (annotation in field.annotations) {
                when (annotation) {
                    is UseCase -> {
                        field.isAccessible = true
                        val id = annotation.value
                        useCases.put(id, field.get(this) as BaseUseCase<*>)
                    }
                }
            }
        }
    }

    fun restart() {
        executeAllFailedUseCases()
    }

    fun <M> executeUseCase(baseUseCase: BaseUseCase<M>) {
        loadingIndicatorLiveData.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = baseUseCase.execute()
                withContext(Dispatchers.Main) {
                    result.apply {
                        when (isSuccessful) {
                            true -> {
                                loadingIndicatorLiveData.value = false
                                onSuccess(result.body())
                            }
                            else -> handleAPIError(result.errorBody()!!.string())
                        }
                    }
                }
            } catch (ex: Exception) {
                handleNoInternetConnectionError(baseUseCase.id)
            }
        }
    }

    private fun handleAPIError(error: String) {
        loadingIndicatorLiveData.value = false
        try {
            val jObjError = JSONObject(error)
            val errorMessage = jObjError.getJSONObject(ERROR).getString(MESSAGE)
            errorLiveData.value = APIException(errorMessage, ErrorType.HTTP)

        } catch (e: Exception) {
            errorLiveData.value = APIException("", ErrorType.UNEXPECTED)
        }
    }

    private suspend fun handleNoInternetConnectionError(useCaseId: Int) {
        withContext(Dispatchers.Main) {
            loadingIndicatorLiveData.value = false
            errorLiveData.value = APIException("", ErrorType.NETWORK)
            onError(useCaseId)
        }
    }

    private fun executeAllFailedUseCases() {
        for (useCase in failedUseCasesList) {
            executeUseCase(useCase)
        }
        failedUseCasesList.clear()
    }

    abstract fun <M> onSuccess(response: M?)

    private fun onError(id: Int) {
        addUseCaseToFailedList(id)
    }

    /**
     * keeps reference of this useCase to reExecute it when presenter restarts.
     *
     * @param id id of the failed useCase.
     */
    private fun addUseCaseToFailedList(id: Int) {
        if (useCases.size() > 0) {
            val useCase = useCases.get(id)
            if (useCase != null) {
                failedUseCasesList.add(useCase)
            }
        }
    }
}