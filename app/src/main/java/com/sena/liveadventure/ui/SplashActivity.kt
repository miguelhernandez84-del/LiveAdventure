package com.sena.liveadventure.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.sena.liveadventure.R

class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private var progreso = 0
    private lateinit var barra: ProgressBar

    private val avanzar = object : Runnable {
        override fun run() {
            progreso += 1
            barra.progress = progreso
            if (progreso < 100) {
                handler.postDelayed(this, 30) // 100 pasos x 30 ms = 3 segundos
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        barra = findViewById(R.id.progressSplash)
        barra.max = 100
        handler.post(avanzar)
    }

    override fun onDestroy() {
        handler.removeCallbacks(avanzar)
        super.onDestroy()
    }
}