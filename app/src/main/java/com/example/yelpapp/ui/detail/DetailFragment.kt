package com.example.yelpapp.ui.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.yelpapp.R
import com.example.yelpapp.databinding.FragmentDetailBinding
import com.example.yelpapp.ui.common.launchAndCollect
import com.example.yelpapp.ui.common.loadUrl

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val navArgs by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel> { DetailViewModelFactory(navArgs.business) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        launchAndCollect(viewModel.state) { binding.updateUI(it) }
    }

    private fun FragmentDetailBinding.updateUI(state : DetailViewModel.UiState){
        state.business?.let {
            backdrop.loadUrl(it.image_url)
            toolbar.title = it.name
            summary.text = buildSpannedString {

                bold { append("City: ") }
                appendLine(it.city)

                bold { append("Address: ") }
                appendLine(it.address)

                bold { append("Phone: ") }
                appendLine(it.phone)

                bold { append("Price: ") }
                appendLine(it.price)
            }
        }
    }
}