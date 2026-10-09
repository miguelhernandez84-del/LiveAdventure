package com.sena.liveadventure.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sena.liveadventure.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón para redirigir al Registro
        binding.btnIrARegistro.setOnClickListener {
            val intent = android.content.Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        // Botón para iniciar sesión
        binding.btnLogin.setOnClickListener {
            val correo = binding.etCorreo.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (correo.isNotEmpty() && password.isNotEmpty()) {
                // Simulamos éxito de login y guardamos la sesión localmente
                val sharedPreferences = getSharedPreferences("LiveAdventurePrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putBoolean("IS_LOGGED_IN", true).apply()

                Toast.makeText(this, "¡Bienvenido a LiveAdventure!", Toast.LENGTH_SHORT).show()

                // Cerramos el Login y regresamos a la pantalla anterior
                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}