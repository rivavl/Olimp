package com.marina.olimp.presentation.detail.viewmodel

import com.marina.olimp.presentation.entity.ServiceUI


sealed interface DetailUiState {

    object Initial : DetailUiState

    object Loading : DetailUiState

    data class Content(val data: ServiceUI) : DetailUiState

    data class Error(val message: String?) : DetailUiState
}