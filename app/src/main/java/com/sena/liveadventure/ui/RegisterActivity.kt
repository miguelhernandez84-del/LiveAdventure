package com.sena.liveadventure.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sena.liveadventure.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarUsuario.setOnClickListener {
            val nombre = binding.etNombreRegistro.text.toString().trim()
            val correo = binding.etCorreoRegistro.text.toString().trim()
            val password = binding.etPasswordRegistro.text.toString().trim()

            if (nombre.isNotEmpty() && correo.isNotEmpty() && password.isNotEmpty()) {
                // Guardamos la sesión como activa en SharedPreferences
                val sharedPreferences = getSharedPreferences("LiveAdventurePrefs", MODE_PRIVATE)
                sharedPreferences.edit().putBoolean("IS_LOGGED_IN", true).apply()

                Toast.makeText(this, "¡Cuenta creada con éxito! Bienvenido, $nombre", Toast.LENGTH_LONG).show()

                // Cerramos las pantallas de registro y login para regresar directamente a la app
                setResult(RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}