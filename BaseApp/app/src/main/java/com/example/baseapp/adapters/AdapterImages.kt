package com.example.baseapp.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapp.R
import com.example.baseapp.classes.Imagem
import com.squareup.picasso.Picasso

class AdapterImages(private val context: Context, private val arrayList: ArrayList<Imagem>) :
    RecyclerView.Adapter<AdapterImages.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.gallery_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_gallery, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayList[position]
        if(item.desc_imagem != null && item.desc_imagem!!.isBlank()) {
            val imageView = holder.imageView
            val bytes = Base64.decode(arrayList[position].base64_imagem, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            imageView.setImageBitmap(bitmap)
        }
    }
}