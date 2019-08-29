package com.example.ricky.misoficios.adaptador

import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.Oficios2
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.itm_carpetas.view.*

class AdapterCarpetas(var list: ArrayList<Carpetas>, var fragmentManager: FragmentManager):
    RecyclerView.Adapter<AdapterCarpetas.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_carpetas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItems(data: Carpetas){
            val tvCarpetas: TextView = itemView.findViewById(R.id.tvCarpetas)

            tvCarpetas.text = data.Nombre

        }
    }
}

