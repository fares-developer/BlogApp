package com.example.blogapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//Esta clase se encarga de devolver el item al que queremos acceder del viewHolder
abstract class BaseViewHolder<T> (itemView: View):RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
}