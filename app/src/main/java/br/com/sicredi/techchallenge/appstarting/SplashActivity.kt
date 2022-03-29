package br.com.sicredi.techchallenge.appstarting

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.sicredi.techchallenge.R
import br.com.sicredi.techchallenge.features.events.presentation.view.activities.MainActivity

// Suppressed because the new SplashScreen library for Android 12 does not
// support Android API 19, that was minSdkVersion for this project
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
                finish()
            },
            500
        )
    }
}