package com.marina.olimp.presentation.list.viewmodel

import com.marina.olimp.presentation.entity.ServiceUI


sealed interface ListUiState {

    object Initial : ListUiState

    object Loading : ListUiState

    data class Content(val data: List<ServiceUI>) : ListUiState

    data class Error(val message: String?) : ListUiState
}