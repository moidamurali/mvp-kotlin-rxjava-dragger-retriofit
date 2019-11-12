package com.es.developine.ui.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.es.developine.R
import com.es.developine.data.MovieData
import kotlinx.android.synthetic.main.movie_list_item.view.*
import java.io.InputStream


class MovieAdapter(val movieItems: List<MovieData>, val context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false))

    }

    override fun getItemCount(): Int {
        return movieItems?.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.itemView.tv_movie_title.text = movieItems.get(position).title
        holder?.itemView.tv_rating.text = movieItems.get(position).rating.toString()
        holder?.itemView.tv_release_year.text = movieItems.get(position).releaseYear.toString()
        var genere: String? = null
        for(i in 0 .. movieItems.get(position).genre.size-1){
            if(genere==null) {
                genere = movieItems.get(position).genre.get(i)
            }else{
                genere = genere  + "," + movieItems.get(position).genre.get(i)
            }
        }
        holder?.itemView.tv_genre.text = genere
        holder?.loadimg  = loadImage(holder?.itemView.icon_view).execute(movieItems.get(position).image) as loadImage
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var loadimg : loadImage? = null

    }

    class loadImage(val imageView: ImageView): AsyncTask<String,Void,Bitmap>(){

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            //To change body of created functions use File | Settings | File Templates.
            val imageURL = params[0]
            var bitmap: Bitmap? = null

            try {
                val input: InputStream? = java.net.URL(imageURL).openStream()
                bitmap = BitmapFactory.decodeStream(input)
            } catch (e: Exception) {
                Log.e("MyApp", e.message)
            }

            return bitmap
        }


        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if (result != null) {
                imageView.setImageBitmap(result)
            }
        }

    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder?.loadimg?.cancel(true)
    }

}