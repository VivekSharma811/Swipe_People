package com.example.swipepeople.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swipepeople.data.Dao
import com.example.swipepeople.data.model.ArchivedUser
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.network.datasource.PeopleDatasource
import com.example.swipepeople.util.DataState

class PeopleRepositoryImpl constructor(
    private val peopleDatasource: PeopleDatasource,
    private val dao: Dao
) : PeopleRepository {

    override suspend fun getAllPeople(): MutableLiveData<DataState<PeopleResponse>> {
        return peopleDatasource.getAllPeople()
    }

    override suspend fun archiveUser(archivedUser: ArchivedUser) {
        dao.insert(archivedUser)
    }

    override suspend fun getAllArchivedUsers(): LiveData<List<ArchivedUser>> {
        return dao.getArchivedPeople()
    }
}