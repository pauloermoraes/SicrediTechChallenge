package br.com.sicredi.techchallenge.features.events.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.features.events.presentation.view.navigation.NavigationFlow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationFlow.navigateToEvents(supportFragmentManager)
    }

    override fun onBackPressed() {
        NavigationFlow.navigateBack(this)
    }

}