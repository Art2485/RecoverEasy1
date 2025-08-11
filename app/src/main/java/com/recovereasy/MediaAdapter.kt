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

class MediaAdapter :
    ListAdapter<MediaItem, MediaAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<MediaItem>() {
            override fun areItemsTheSame(a: MediaItem, b: MediaItem) = a.uri == b.uri
            override fun areContentsTheSame(a: MediaItem, b: MediaItem) = a == b
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_media_item, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val thumb: ImageView = view.findViewById(R.id.thumb)
        private val name: TextView = view.findViewById(R.id.name)
        fun bind(item: MediaItem) {
            name.text = item.name
            thumb.load(item.uri) {
                crossfade(true)
            }
        }
    }
    }
