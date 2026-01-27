package com.dam.vmappweb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.vmappweb.databinding.FilaPostBinding

class PostAdapter: RecyclerView.Adapter<PostViewHolder>() {
    var arrayPosts = mutableListOf<Post>()

    private lateinit var listener: PostAdapterCallBack

    interface PostAdapterCallBack {
        fun postFav(post: Post)
    }

    fun setOnClickPostListener(listener: PostAdapterCallBack) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding = FilaPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = PostViewHolder(binding)
        return viewHolder
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = arrayPosts[position]
        holder.bind(post)

        //O también: holder.bind(arrayPosts[position])

        //guardar favorito
        holder.getToolbar().setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_fav -> {

                    //Aquí realizo el guardado
                    listener.postFav(arrayPosts[position])

                    true
                }
                else -> false
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayPosts.size
    }

    fun setPosts(posts: List<Post>) {
        this.arrayPosts = posts as MutableList<Post>
        notifyDataSetChanged()
    }

}