package com.skills.newsapp.data.remote

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skills.newsapp.utils.Resource
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

/**
 *  Class is responsible for manging calls from network or from Database and sync data with database
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) { result.value =
            Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                try {
                    if(isExternalCall()){
                        fetchFromExternal(dbResult)
                    }else{
                        fetchFromNetwork(dbResult)
                    }
                } catch (e: Exception) {
                    Log.e("NetworkBoundResource", "An error happened: $e")
                    setValue(Resource.error(e, loadFromDb()))
                }
            } else {
                Log.d(NetworkBoundResource::class.java.name, "Return data from local database")
                setValue(Resource.success(dbResult))
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>


    private suspend fun fetchFromNetwork(dbResult: ResultType) {
        Log.d(NetworkBoundResource::class.java.name, "Fetch data from network")
        setValue(Resource.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
        val apiResponse = createCallAsync()?.await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        saveCallResults(processResponse(apiResponse))
        setValue(Resource.success(loadFromDb()))
    }

    private suspend fun fetchFromExternal(dbResult: ResultType) {
        Log.d(NetworkBoundResource::class.java.name, "Fetch data from External")
        setValue(Resource.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
        runBlocking {
            val apiResponse = createExternalCall()
            Log.e(NetworkBoundResource::class.java.name, "Data fetched from External")
            saveCallResults(processResponse(apiResponse))
            setValue(Resource.success(loadFromDb()))
        }
    }


    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Log.d(NetworkBoundResource::class.java.name, "Resource: "+newValue)
        if (result.value != newValue) result.postValue(newValue)
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType?): ResultType?

    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun isExternalCall(): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>?

    @MainThread
    protected abstract fun createExternalCall(): RequestType?
}