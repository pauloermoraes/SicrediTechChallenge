package br.com.sicredi.techchallenge.features.events.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.databinding.EventDetailsFragmentBinding
import br.com.sicredi.techchallenge.features.events.domain.mappers.DoublePriceToStringFormattedPriceMapper
import br.com.sicredi.techchallenge.features.events.domain.mappers.LongDateToStringFormattedDateMapper
import br.com.sicredi.techchallenge.features.events.presentation.view.constants.ParameterConstants
import br.com.sicredi.techchallenge.features.events.presentation.view.navigation.NavigationFlow
import br.com.sicredi.techchallenge.features.events.presentation.viewmodel.EventDetailsViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventDetailsFragment : Fragment(R.layout.event_details_fragment) {

    private lateinit var binding: EventDetailsFragmentBinding
    private val viewModel: EventDetailsViewModel by viewModel { parametersOf(eventId) }

    private lateinit var eventId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventId = arguments?.getString(ParameterConstants.EVENT_ID)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventDetailsFragmentBinding.bind(view)
        configureViewModels()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEventDetails()
    }

    private fun configureViewModels() {

        viewModel.eventDetailsLiveData.observe(viewLifecycleOwner) { _event ->
            binding.tvTitle.text = _event.title
            binding.tvPrice.text = DoublePriceToStringFormattedPriceMapper().map(_event.price)
            binding.tvDescription.text = _event.description
            binding.tvEventDate.setText(LongDateToStringFormattedDateMapper().map(_event.date))
            binding.btnCheckIn.setOnClickListener {
                onClickCheckIn(_event.id, _event.image)
            }
            Glide.with(requireActivity())
                .load(_event.image)
                .centerCrop()
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.ivEventImageBanner)
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { _loading ->
            val progressBar = binding.progressBar.root
            progressBar.visibility = if (_loading) View.VISIBLE else View.GONE
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { _throwable ->
            _throwable?.let {
                binding.clError.root.visibility = View.VISIBLE
            } ?: run {
                binding.clError.root.visibility = View.GONE
            }
        }
    }

    private fun onClickCheckIn(eventId: String, eventImageUrl: String) {
        NavigationFlow.navigateToEventCheckIn(
            requireActivity().supportFragmentManager,
            eventId,
            eventImageUrl
        )
    }

}