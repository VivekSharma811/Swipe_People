package com.example.swipepeople.repository

import androidx.lifecycle.MutableLiveData
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.network.datasource.PeopleDatasource
import com.example.swipepeople.util.DataState

class PeopleRepositoryImpl constructor(
    private val peopleDatasource: PeopleDatasource
) : PeopleRepository {

    override suspend fun getAllPeople(): MutableLiveData<DataState<PeopleResponse>> {
        return peopleDatasource.getAllPeople()
    }
}