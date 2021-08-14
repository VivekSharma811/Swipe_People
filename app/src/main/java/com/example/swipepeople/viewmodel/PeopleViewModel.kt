package com.example.swipepeople.viewmodel

import android.app.PendingIntent
import androidx.lifecycle.*
import com.example.swipepeople.data.model.ArchivedUser
import com.example.swipepeople.data.model.PeopleResponse
import com.example.swipepeople.repository.PeopleRepository
import com.example.swipepeople.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private var _peopleLiveData: MutableLiveData<DataState<PeopleResponse>> = MutableLiveData()
    val peopleLiveData: LiveData<DataState<PeopleResponse>>
        get() = _peopleLiveData

    private var _archivedUsersLiveData: LiveData<List<ArchivedUser>> = MutableLiveData()
    val archivedUsersLiveData: LiveData<List<ArchivedUser>>
        get() = _archivedUsersLiveData

    suspend fun getAllPeople() {
        viewModelScope.launch {
            _peopleLiveData = peopleRepository.getAllPeople()
        }
    }

    suspend fun archiveUser(archivedUser: ArchivedUser) {
        viewModelScope.launch {
            peopleRepository.archiveUser(archivedUser)
        }
    }

    suspend fun getArchivedUsers() {
        viewModelScope.launch {
            _archivedUsersLiveData = peopleRepository.getAllArchivedUsers()
        }
    }

}