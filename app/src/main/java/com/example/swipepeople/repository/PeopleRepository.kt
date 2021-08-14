package com.example.swipepeople.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swipepeople.data.model.ArchivedUser
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.util.DataState

interface PeopleRepository {

    suspend fun getAllPeople(): MutableLiveData<DataState<PeopleResponse>>

    suspend fun archiveUser(archivedUser: ArchivedUser)

    suspend fun getAllArchivedUsers(): LiveData<List<ArchivedUser>>
}