package com.sena.liveadventure.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sena.liveadventure.databinding.ActivityMainBinding
import com.sena.liveadventure.model.Servicio
import com.sena.liveadventure.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Declaramos ViewBinding para la actividad principal
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos el RecyclerView para que sea una lista vertical
        binding.recyclerServicios.layoutManager = LinearLayoutManager(this)

        // Llamamos a la API para obtener los servicios del catálogo web
        cargarServicios()

        binding.btnVerMisReservas.setOnClickListener {
            val intent = android.content.Intent(this, MisReservasActivity::class.java)
            startActivity(intent)
        }

        binding.btnCerrarSesion.setOnClickListener {
            val sharedPreferences = getSharedPreferences("LiveAdventurePrefs", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("IS_LOGGED_IN", false).apply()

            Toast.makeText(this, "Has cerrado sesión correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarServicios() {
        // Simulamos una lista de servicios locales para probar la interfaz y el flujo sin backend
        val listaServicios = listOf(
            Servicio(1, "Rafting en Río Pauto", "Emoción pura navegando rápidos de clase III y IV.", 120000.0, ""),
            Servicio(2, "Bungee Jumping", "Salto al vacío desde 50 metros de altura con total seguridad.", 150000.0, ""),
            Servicio(3, "Parapente en los Andes", "Vuela sobre paisajes increíbles con instructores certificados.", 180000.0, ""),
            Servicio(4, "Torrentismo en Cascada", "Descenso extremo por el caudal de una hermosa cascada.", 95000.0, "")
        )

        // Creamos el adaptador y manejamos el clic en el botón "Reservar"
        val adapter = ServicioAdapter(listaServicios) { servicioSeleccionado ->
            // Verificamos si el usuario ha iniciado sesión
            val sharedPreferences = getSharedPreferences("LiveAdventurePrefs", MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false)

            if (isLoggedIn) {
                // Guardamos la reserva localmente en SharedPreferences
                val sharedPreferences = getSharedPreferences("LiveAdventurePrefs", MODE_PRIVATE)
                val reservasActuales = sharedPreferences.getStringSet("MIS_RESERVAS", mutableSetOf()) ?: mutableSetOf()

                // Añadimos el título y precio del servicio
                val nuevaReserva = "${servicioSeleccionado.titulo} - $${servicioSeleccionado.precio}"
                reservasActuales.add(nuevaReserva)

                sharedPreferences.edit().putStringSet("MIS_RESERVAS", reservasActuales).apply()

                Toast.makeText(
                    this@MainActivity,
                    "¡Reserva exitosa para: ${servicioSeleccionado.titulo}!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // Si no ha iniciado sesión, lo redirigimos al Login
                Toast.makeText(
                    this@MainActivity,
                    "Debes iniciar sesión para realizar una reserva",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = android.content.Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.recyclerServicios.adapter = adapter
    }
}