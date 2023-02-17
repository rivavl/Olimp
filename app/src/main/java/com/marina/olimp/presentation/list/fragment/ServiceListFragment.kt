package com.marina.olimp.presentation.list.fragment

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.marina.olimp.R
import com.marina.olimp.databinding.FragmentServicesListBinding
import com.marina.olimp.presentation.base.BaseFragment
import com.marina.olimp.presentation.detail.fragment.ServiceDetailFragment
import com.marina.olimp.presentation.entity.ServiceUI
import com.marina.olimp.presentation.list.ServiceAdapter
import com.marina.olimp.presentation.list.viewmodel.ListUiState
import com.marina.olimp.presentation.list.viewmodel.ListViewModel

class ServiceListFragment : BaseFragment<FragmentServicesListBinding, ListViewModel>(
    FragmentServicesListBinding::inflate
) {

    override val viewModel: ListViewModel by viewModels { TODO() }

    private var recyclerView: RecyclerView? = null

    override fun initView() {
        setupRecyclerView()
    }

    override fun observeData() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: ListUiState) {
        when (state) {
            ListUiState.Initial -> Unit
            ListUiState.Loading -> showProgress()
            is ListUiState.Content -> showContent(state.data)
            is ListUiState.Error -> showError()
        }
    }

    private fun showProgress() = with(binding) {
        progressBar.isVisible = true
        rvServicesList.isVisible = false
        errorGroup.btnTryAgain.isVisible = false
        errorGroup.tvError.isVisible = false
    }

    private fun showContent(data: List<ServiceUI>) = with(binding) {
        progressBar.isVisible = false
        rvServicesList.isVisible = true
        (rvServicesList.adapter as ServiceAdapter).submitList(data)
        errorGroup.btnTryAgain.isVisible = false
        errorGroup.tvError.isVisible = false
    }

    private fun showError() = with(binding) {
        progressBar.isVisible = false
        rvServicesList.isVisible = false
        errorGroup.btnTryAgain.isVisible = true
        errorGroup.tvError.isVisible = true
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvServicesList
        recyclerView?.apply {
            adapter = ServiceAdapter()
        }
        (recyclerView!!.adapter as ServiceAdapter).onServiceClick = {
            handleServiceClick(it)
        }
    }

    private fun handleServiceClick(service: ServiceUI) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, ServiceDetailFragment.newInstance(service))
            .commit()
    }

    companion object {
        fun newInstance(): ServiceListFragment {
            return ServiceListFragment()
        }
    }
}