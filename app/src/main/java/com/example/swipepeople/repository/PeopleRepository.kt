package com.example.swipepeople.repository

import androidx.lifecycle.MutableLiveData
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.util.DataState

interface PeopleRepository {

    suspend fun getAllPeople() : MutableLiveData<DataState<PeopleResponse>>
}