package br.com.sicredi.techchallenge.features.events.di.extension

import br.com.sicredi.techchallenge.appstarting.SicrediApplication
import br.com.sicredi.techchallenge.features.events.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun SicrediApplication.startSicrediKoin() {
    val applicationContext = this

    startKoin {
        androidContext(applicationContext)
        printLogger(Level.NONE)
        fragmentFactory()
        modules(
            listOf(
                koinModuleUseCases,
                koinModuleRepositories,
                koinModuleExternal,
                koinModuleViewModels,
                koinModuleFragments
            )
        )
    }
}