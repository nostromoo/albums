package com.romain.pedepoy.albums.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.romain.pedepoy.albums.data.Album
import com.romain.pedepoy.albums.databinding.AlbumItemBinding

class AlbumAdapter :
    PagedListAdapter<Album, AlbumAdapter.ViewHolder>(
        AlbumDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)
        album?.let {
            holder.bind(album)
        }
    }

    class ViewHolder(private val binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(album: Album) {
            binding.album = album
        }
    }
}

private class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}