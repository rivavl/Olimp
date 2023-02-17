package com.marina.olimp.presentation.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.olimp.domain.usecase.GetServicesUseCase
import com.marina.olimp.presentation.mapper.ServiceUIMapper
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class ListViewModel(
    private val useCase: GetServicesUseCase,
    private val converter: ServiceUIMapper
) : ViewModel() {

    private val _state = MutableLiveData<ListUiState>(ListUiState.Initial)
    val state: LiveData<ListUiState> get() = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.value = ListUiState.Loading
            try {
                val characters = useCase().map { converter.convert(it) }
                _state.value = ListUiState.Content(characters)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = ListUiState.Error(ex.message)
            }
        }
    }
}