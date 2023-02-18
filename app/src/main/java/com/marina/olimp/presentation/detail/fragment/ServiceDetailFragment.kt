package com.marina.olimp.presentation.detail.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import com.marina.olimp.app.App
import com.marina.olimp.databinding.FragmentServiceDetailBinding
import com.marina.olimp.di.AppContainer
import com.marina.olimp.presentation.base.BaseFragment
import com.marina.olimp.presentation.detail.viewmodel.DetailUiState
import com.marina.olimp.presentation.detail.viewmodel.DetailViewModel
import com.marina.olimp.presentation.entity.ServiceUI
import com.marina.olimp.presentation.util.load

class ServiceDetailFragment : BaseFragment<FragmentServiceDetailBinding, DetailViewModel>(
    FragmentServiceDetailBinding::inflate
) {

    override val container: AppContainer by lazy {
        (requireActivity().application as App).container
    }

    override val viewModel: DetailViewModel by lazy {
        container.detailViewModel
    }

    private val item by lazy {
        val args = requireArguments()
        args.getParcelable<ServiceUI>(SERVICE_ITEM)!!
    }

    override fun initView() {
        viewModel.saveData(item)
        binding.errorGroup1.btnTryAgain.setOnClickListener {
            viewModel.saveData(item)
        }
        binding.tvServiceLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.serviceLink))
            startActivity(intent)
        }
        requireActivity().title = item.name
    }

    override fun observeData() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: DetailUiState) {
        when (state) {
            DetailUiState.Initial -> Unit
            DetailUiState.Loading -> showProgress()
            is DetailUiState.Content -> showContent(state.data)
            is DetailUiState.Error -> showError()
        }
    }

    private fun showProgress() = with(binding) {
        progressBar.isVisible = true
        ivServiceIcon.isVisible = false
        tvServiceName.isVisible = false
        tvServiceDescription.isVisible = false
        tvServiceLink.isVisible = false
        errorGroup1.btnTryAgain.isVisible = false
        errorGroup1.tvError.isVisible = false
    }

    private fun showContent(data: ServiceUI) = with(binding) {
        progressBar.isVisible = false

        ivServiceIcon.isVisible = true
        ivServiceIcon.load(data.imageURL, requireContext())

        tvServiceName.isVisible = true
        tvServiceName.text = data.name

        tvServiceDescription.isVisible = true
        tvServiceDescription.text = data.description

        tvServiceLink.isVisible = true
        tvServiceLink.text = data.serviceLink

        errorGroup1.btnTryAgain.isVisible = false
        errorGroup1.tvError.isVisible = false
    }

    private fun showError() = with(binding) {
        progressBar.isVisible = false
        ivServiceIcon.isVisible = false
        tvServiceName.isVisible = false
        tvServiceDescription.isVisible = false
        tvServiceLink.isVisible = false
        errorGroup1.btnTryAgain.isVisible = true
        errorGroup1.tvError.isVisible = true
    }

    companion object {

        private const val SERVICE_ITEM = "service_item"

        fun newInstance(item: ServiceUI): ServiceDetailFragment {
            return ServiceDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(SERVICE_ITEM, item)
                }
            }
        }
    }
}