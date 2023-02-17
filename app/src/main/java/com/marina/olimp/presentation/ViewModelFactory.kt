package com.marina.olimp.presentation

import com.marina.olimp.domain.usecase.GetServicesUseCase
import com.marina.olimp.presentation.list.viewmodel.ListViewModel
import com.marina.olimp.presentation.mapper.ServiceUIMapper

interface Factory<T> {
    fun create(): T
}

class ListViewModelFactory(
    private val useCase: GetServicesUseCase,
    private val mapper: ServiceUIMapper
) : Factory<ListViewModel> {
    override fun create(): ListViewModel {
        return ListViewModel(useCase, mapper)
    }
}