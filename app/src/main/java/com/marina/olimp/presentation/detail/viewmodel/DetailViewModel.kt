package com.marina.olimp.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marina.olimp.presentation.entity.ServiceUI

class DetailViewModel : ViewModel() {

    private val _state = MutableLiveData<DetailUiState>(DetailUiState.Initial)
    val state: LiveData<DetailUiState> get() = _state

    fun saveData(item: ServiceUI) {
        _state.value = DetailUiState.Content(item)
    }
}