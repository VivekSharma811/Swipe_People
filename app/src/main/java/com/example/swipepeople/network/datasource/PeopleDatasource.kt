package com.example.swipepeople.network.datasource

import androidx.lifecycle.MutableLiveData
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.util.DataState

interface PeopleDatasource {

    suspend fun getAllPeople() : MutableLiveData<DataState<PeopleResponse>>
}