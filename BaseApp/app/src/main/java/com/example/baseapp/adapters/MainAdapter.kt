package com.example.baseapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.R
import com.example.baseapp.classes.Modulo

class MainAdapter(private val context: Context, private val arrayList: ArrayList<Modulo>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNumero = itemView.findViewById<TextView>(R.id.text_number)
        val textModulo = itemView.findViewById<TextView>(R.id.text_modulo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.main_adapter, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]
        holder.textModulo.setText("# ${item.modulo}")
        holder.textNumero.setText(item.numero.toString())
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}