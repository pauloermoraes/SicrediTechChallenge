package br.com.sicredi.techchallenge.features.events.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.databinding.EventCheckInFragmentBinding
import br.com.sicredi.techchallenge.features.events.presentation.view.constants.ParameterConstants
import br.com.sicredi.techchallenge.features.events.presentation.view.navigation.NavigationFlow
import br.com.sicredi.techchallenge.features.events.presentation.viewmodel.EventCheckInViewModel
import br.com.sicredi.techchallenge.global.exceptions.InvalidEmailException
import br.com.sicredi.techchallenge.global.exceptions.InvalidNameException
import br.com.sicredi.techchallenge.global.tools.AlertDialogTools
import br.com.sicredi.techchallenge.global.tools.KeyboardTools
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventCheckInFragment : Fragment(R.layout.event_check_in_fragment) {

    private lateinit var binding: EventCheckInFragmentBinding
    private val viewModel: EventCheckInViewModel by viewModel { parametersOf(eventId) }

    private lateinit var eventId: String
    private lateinit var eventImageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventId = arguments?.getString(ParameterConstants.EVENT_ID)!!
        eventImageUrl = arguments?.getString(ParameterConstants.EVENT_IMAGE_URL)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventCheckInFragmentBinding.bind(view)

        loadEventImage()


        configureObservers()
        binding.btnEventCheckIn.setOnClickListener {
            KeyboardTools.hide(requireActivity())
            viewModel.eventCheckIn(binding.etName.text.toString(), binding.etEmail.text.toString())
        }
    }

    private fun configureObservers() {
        viewModel.eventCheckInLiveData.observe(viewLifecycleOwner) { _checkedIn ->
            if (_checkedIn) {
                NavigationFlow.navigateBackTo(
                    requireActivity(),
                    EventsFragment::class.qualifiedName!!
                )
            }
            AlertDialogTools.alertDialog(
                requireActivity(),
                getString(if (_checkedIn) R.string.success_label else R.string.error_label),
                getString(
                    if (_checkedIn) R.string.event_check_in_success_label
                    else R.string.event_check_in_error_label
                ),
                false
            )
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { _loading ->
            val progressBar = binding.progressBar.root
            progressBar.visibility = if (_loading) View.VISIBLE else View.GONE
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { _throwable ->
            _throwable?.let {
                when (_throwable) {
                    is InvalidNameException -> binding.etName.error = getString(
                        R.string.event_check_in_invalid_name_label
                    )
                    is InvalidEmailException -> binding.etEmail.error = getString(
                        R.string.event_check_in_invalid_email_label
                    )
                }
            } ?: clearEtErrors()
        }
    }

    private fun clearEtErrors() {
        binding.etName.error = null
        binding.etEmail.error = null
    }

    private fun loadEventImage() {
        Glide.with(requireContext())
            .load(eventImageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_image_placeholder)
            .into(binding.ivEventImageBanner)
    }
}