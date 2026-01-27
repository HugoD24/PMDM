package com.dam.vmappweb

import android.text.Html
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.dam.vmappweb.bbdd.PostFavoritos
import com.dam.vmappweb.databinding.FilaPostBinding
import java.text.SimpleDateFormat
import java.util.Locale

class FavoritosViewHolder(
    private val binding: FilaPostBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.tbCardFav.inflateMenu(R.menu.menu_eliminar)
    }

    fun bind(post: PostFavoritos) {

        binding.tvPostTitulo.text = Html.fromHtml(
            post.title,
            Html.FROM_HTML_MODE_COMPACT
        ).toString()

        binding.tvPostExtracto.text = Html.fromHtml(
            post.content,
            Html.FROM_HTML_MODE_COMPACT
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

