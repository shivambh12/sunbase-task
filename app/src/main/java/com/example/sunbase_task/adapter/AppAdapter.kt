package com.example.sunbase_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunbase_task.R
import com.example.sunbase_task.models.dataItem
import kotlinx.android.synthetic.main.item_view.view.*

class AppAdapter: PagingDataAdapter<dataItem, AppAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context).
        inflate(R.layout.item_view,parent,false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val data=getItem(position)
        holder.itemView.apply {
            Glide.with(this).load(data?.urls?.small).into(image)
        }
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<dataItem>() {
            override fun areItemsTheSame(oldItem: dataItem, newItem: dataItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: dataItem, newItem: dataItem) =
                oldItem == newItem
        }
    }
}