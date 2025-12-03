package com.dam.exnov25

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderPasos (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var tvPasos: TextView
    init {
        tvPasos=itemView.findViewById(R.id.tvPasos)
    }
}