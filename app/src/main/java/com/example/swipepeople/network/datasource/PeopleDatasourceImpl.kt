package com.example.swipepeople.network.datasource

import androidx.lifecycle.MutableLiveData
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.network.ApiService
import com.example.swipepeople.util.DataState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PeopleDatasourceImpl constructor(
    private val disposable: CompositeDisposable,
    private val apiService: ApiService
) : PeopleDatasource {
    override suspend fun getAllPeople(): MutableLiveData<DataState<PeopleResponse>> {
        val peopleLiveData = MutableLiveData<DataState<PeopleResponse>>()

        peopleLiveData.value = DataState.Loading

        disposable.add(
            apiService.getPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PeopleResponse>() {

                    override fun onSuccess(t: PeopleResponse) {
                        peopleLiveData.value = DataState.Success(t)
                    }

                    override fun onError(e: Throwable) {
                        peopleLiveData.value = DataState.Error(e.localizedMessage!!)
                    }
                })
        )

        return peopleLiveData
    }
}