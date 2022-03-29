package br.com.sicredi.techchallenge.appstarting

import androidx.multidex.MultiDexApplication
import br.com.sicredi.techchallenge.features.events.di.extension.startSicrediKoin


class SicrediApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startSicrediKoin()
    }
}