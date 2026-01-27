package com.dam.vmappweb

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.dam.vmappweb.databinding.FilaPostBinding
import java.text.SimpleDateFormat
import java.util.Locale

class PostViewHolder (
    private val binding: FilaPostBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
       binding.tbCardFav.inflateMenu(R.menu.menu_favorito)
    }

    fun bind(post: Post) {


        binding.tvPostTitulo.text = android.text.Html.fromHtml(
            post.title.rendered,
            android.text.Html.FROM_HTML_MODE_COMPACT
        ).toString()


        binding.tvPostExtracto.text = android.text.Html.fromHtml(
            post.content.rendered,
            android.text.Html.FROM_HTML_MODE_COMPACT
        ).toString()


        binding.tvPostFecha.text = formatPostDate(post.date)


    }

    fun getToolbar(): Toolbar {
        return binding.tbCardFav
    }

    private fun formatPostDate(isoDate: String): String {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val date = inputFormat.parse(isoDate)


            val outputFormat = SimpleDateFormat("dd MMMM, yyyy", Locale("es", "ES"))
            return outputFormat.format(date)
        } catch (e: Exception) {
            return isoDate.split("T")[0] // Fallback simple (YYYY-MM-DD)
        }
    }
}
