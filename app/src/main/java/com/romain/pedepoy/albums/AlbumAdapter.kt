package com.romain.pedepoy.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.romain.pedepoy.albums.data.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumAdapter(private val albums : List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item, parent, false) as View
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(albums[position]?.thumbnailUrl).into(holder.view.thumb);
        holder.view.title.text = albums[position]?.title
    }

    class ViewHolder(val view: View ) : RecyclerView.ViewHolder(view) {


    }

    override fun getItemCount(): Int {
        return albums.size
    }
}
