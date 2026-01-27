package com.dam.vmappweb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.vmappweb.bbdd.PostFavoritos
import com.dam.vmappweb.databinding.FilaPostBinding

class FavoritosAdapter: RecyclerView.Adapter<FavoritosViewHolder>() {
    private var arrayPosts=mutableListOf<PostFavoritos>()
    private lateinit var listener:PostAdapterCallback

    interface PostAdapterCallback{
        fun postSelected(city: PostFavoritos)
        fun postDelete(post: PostFavoritos)
    }
    fun setOnClickPostListener(listener: PostAdapterCallback){
        this.listener=listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        val binding = FilaPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoritosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        holder.bind(arrayPosts[position])
//        holder.itemView.setOnClickListener {
//            listener.postSelected(arrayPosts[position])
//        }
        holder.getToolbar().setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_borrar ->{
                    listener.postDelete(arrayPosts[position])
                    true
                }
                else -> {true}
            }

        }
    }

    override fun getItemCount() = arrayPosts.size

    fun setPosts(results: List<PostFavoritos>) {
        this.arrayPosts= results as MutableList<PostFavoritos>
        notifyDataSetChanged()
    }


}
