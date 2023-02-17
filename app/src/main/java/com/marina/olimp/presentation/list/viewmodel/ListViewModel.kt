package com.marina.olimp.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val _state = MutableLiveData<ListUiState>(ListUiState.Initial)
    val state: LiveData<ListUiState> get() = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {

        }
    }
}