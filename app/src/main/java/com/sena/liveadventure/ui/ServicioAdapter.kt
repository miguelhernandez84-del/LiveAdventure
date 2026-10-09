package com.sena.liveadventure.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sena.liveadventure.databinding.ItemServicioBinding
import com.sena.liveadventure.model.Servicio

class ServicioAdapter(
    private val listaServicios: List<Servicio>,
    private val onReservarClick: (Servicio) -> Unit
) : RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder>() {

    inner class ServicioViewHolder(val binding: ItemServicioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicioViewHolder {
        val binding = ItemServicioBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ServicioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicioViewHolder, position: Int) {
        val servicio = listaServicios[position]
        with(holder.binding) {
            txtTitulo.text = servicio.titulo
            txtDescripcion.text = servicio.descripcion
            txtPrecio.text = "$ ${servicio.precio}"

            // Evento al hacer clic en el botón Reservar de cada tarjeta
            btnReservar.setOnClickListener {
                onReservarClick(servicio)
            }
        }
    }

    override fun getItemCount(): Int = listaServicios.size
}