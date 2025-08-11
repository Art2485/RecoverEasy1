package com.recovereasy

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

data class MediaItem(val name: String, val size: String, val thumbnail: String?)

class MediaAdapter : ListAdapter<MediaItem, MediaAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<MediaItem>() {
            override fun areItemsTheSame(o: MediaItem, n: MediaItem) = o.name == n.name
            override fun areContentsTheSame(o: MediaItem, n: MediaItem) = o == n
        }
    }

    inner class VH(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false)
    ) {
        private val thumb = itemView.findViewById<ImageView>(R.id.thumb)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val size = itemView.findViewById<TextView>(R.id.size)

        fun bind(item: MediaItem) {
            title.text = item.name
            size.text = item.size
            item.thumbnail?.let { url ->
                thumb.load(url) { crossfade(true) }
            } ?: thumb.setImageDrawable(null)
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, v: Int) = VH(p)
    override fun onBindViewHolder(h: VH, pos: Int) = h.bind(getItem(pos))
}
