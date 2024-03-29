package com.es.android.ui.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.es.android.R
import com.es.android.data.PostData
import com.es.android.ui.movie.MovieActivity
import kotlinx.android.synthetic.main.post_list_item.view.*
import java.time.Instant

class PostItemAdapter(val postItems: List<PostData>, val context: Context) : RecyclerView.Adapter<PostItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_list_item, parent, false))

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.itemView.tv_post_title.text = postItems.get(position).title
        holder?.itemView.tv_post_body.text = postItems.get(position).body

        holder?.itemView.setOnClickListener {
            val intent = Intent(context, MovieActivity::class.java)
            // start your next activity
            context.startActivity(intent)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}