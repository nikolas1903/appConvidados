package com.example.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel
import com.example.convidados.view.liestener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView){

    fun bind(guest: GuestModel){
        val textName = itemView.findViewById<TextView>(R.id.tv_name)
        textName.text = guest.name

        textName.setOnClickListener{
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener{

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.tituloExclusao)
                .setMessage(R.string.mensagemExclusao)
                .setPositiveButton(R.string.confirmarExclusao) { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelarExclusao, null)
                .show()

            true
        }

    }
}