package com.marina.olimp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marina.olimp.domain.usecase.GetServicesUseCase
import com.marina.olimp.presentation.detail.viewmodel.DetailViewModel
import com.marina.olimp.presentation.list.viewmodel.ListViewModel
import com.marina.olimp.presentation.mapper.ServiceUIMapper

interface Factory<T> {
    fun create(): T
}

class ListViewModelFactory(
    private val useCase: GetServicesUseCase,
    private val mapper: ServiceUIMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ListViewModel::class.java -> {
                ListViewModel(useCase, mapper) as T
            }
            DetailViewModel::class.java -> {
                DetailViewModel() as T
            }
            else -> {
                throw Exception()
            }
        }
    }
}

class DetailViewModelFactory : Factory<DetailViewModel> {
    override fun create(): DetailViewModel {
        return DetailViewModel()
    }
}