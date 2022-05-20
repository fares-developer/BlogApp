package com.example.firstapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//Esta clase es necesaria para el adaptador principal de las vistas
abstract class BaseViewHolder<T>(itemView: View):RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}