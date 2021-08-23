package com.davidargote.clone_reddit.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.davidargote.clone_reddit.R
import com.davidargote.clone_reddit.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        actionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.orange_500)
        Handler(Looper.getMainLooper()).postDelayed({ changeActivity() }, 2000)

    }

    private fun changeActivity() {
        Intent(this, MainActivity::class.java).run {
            startActivity(this)
            finish()
        }
    }
}