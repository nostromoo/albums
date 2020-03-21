package com.romain.pedepoy.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.romain.pedepoy.albums.data.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumAdapter :
    ListAdapter<Album, AlbumAdapter.ViewHolder>(AlbumDiffCallback()) {

    var albums : List<Album?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item, parent, false) as View
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(getItem(position).thumbnailUrl).into(holder.view.thumb)
        holder.view.title.text = getItem(position).title
    }

    class ViewHolder(val view: View ) : RecyclerView.ViewHolder(view)
}

private class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.Id == newItem.Id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}