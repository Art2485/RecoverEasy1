package com.recovereasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MediaAdapter : ListAdapter<MediaItem, MediaAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<MediaItem>() {
            override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem) =
                oldItem.uri == newItem.uri
            override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_media, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.name.text = item.name
        holder.size.text = item.size.toString()
        holder.thumb.load(item.uri) { crossfade(true) }
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.findViewById(R.id.nameText)
        val size: TextView = v.findViewById(R.id.sizeText)
        val thumb: ImageView = v.findViewById(R.id.thumb)
    }
}
