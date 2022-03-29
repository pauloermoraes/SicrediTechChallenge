package br.com.sicredi.techchallenge.features.events.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.databinding.EventsFragmentBinding
import br.com.sicredi.techchallenge.features.events.presentation.view.adapters.EventAdapter
import br.com.sicredi.techchallenge.features.events.presentation.view.navigation.NavigationFlow
import br.com.sicredi.techchallenge.features.events.presentation.viewmodel.EventsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment(R.layout.events_fragment) {

    private lateinit var binding: EventsFragmentBinding

    private val viewModel: EventsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EventsFragmentBinding.bind(requireView())

        binding.rvEvents.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        configureObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEvents()
    }

    private fun configureObservers() {
        viewModel.eventsLiveData.observe(viewLifecycleOwner) { _events ->
            binding.rvEvents.adapter = EventAdapter(_events, onClickInfo())
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { _loading ->
            val progressBar = binding.progressBar.root
            progressBar.visibility = if (_loading) View.VISIBLE else View.GONE
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { _throwable ->
            _throwable?.let {
                binding.clError.root.visibility = View.VISIBLE
                binding.rvEvents.visibility = View.GONE
            } ?: run {
                binding.clError.root.visibility = View.GONE
                binding.rvEvents.visibility = View.VISIBLE
            }
        }
    }

    private fun onClickInfo(): (eventId: String) -> Unit = { _eventId ->
        NavigationFlow.navigateToEventDetails(requireActivity().supportFragmentManager, _eventId)
    }
}