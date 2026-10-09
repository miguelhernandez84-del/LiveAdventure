package com.sena.liveadventure.network

import com.sena.liveadventure.model.Servicio
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("servicios") // Cambia esto por la ruta real de tu API para listar servicios
    fun listarServicios(): Call<List<Servicio>>
}