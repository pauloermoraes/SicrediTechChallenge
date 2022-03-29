package br.com.sicredi.techchallenge.features.events.di

import br.com.sicredi.techchallenge.features.events.domain.models.Event
import br.com.sicredi.techchallenge.features.events.presentation.view.fragments.EventDetailsFragment
import br.com.sicredi.techchallenge.features.events.presentation.view.fragments.EventsFragment
import br.com.sicredi.techchallenge.features.events.presentation.viewmodel.EventCheckInViewModel
import br.com.sicredi.techchallenge.features.events.presentation.viewmodel.EventDetailsViewModel
import br.com.sicredi.techchallenge.features.events.presentation.viewmodel.EventsViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModuleViewModels = module {

    viewModel { (eventId: String) ->
        EventCheckInViewModel(get(), eventId)
    }

    viewModel { (event: String) ->
        EventDetailsViewModel(get(), event)
    }

    viewModel {
        EventsViewModel(get())
    }
}

val koinModuleFragments = module {
    fragment {
        EventsFragment()
    }

    fragment {
        EventDetailsFragment()
    }
}