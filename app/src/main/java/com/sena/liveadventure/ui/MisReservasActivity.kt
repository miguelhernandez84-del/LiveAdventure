package com.sena.liveadventure.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.sena.liveadventure.databinding.ActivityMisReservasBinding

class MisReservasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMisReservasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos las reservas almacenadas localmente
        val sharedPreferences = getSharedPreferences("LiveAdventurePrefs", MODE_PRIVATE)
        val reservasSet = sharedPreferences.getStringSet("MIS_RESERVAS", emptySet()) ?: emptySet()
        val listaReservas = reservasSet.toList()

        // Mostramos las reservas en un ListView sencillo
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaReservas)
        binding.listViewReservas.adapter = adapter
    }
}