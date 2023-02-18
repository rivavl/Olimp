package com.marina.olimp.presentation.list

import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_HOVER_ENTER
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marina.olimp.R
import com.marina.olimp.databinding.ServiceItemBinding
import com.marina.olimp.presentation.entity.ServiceUI
import com.marina.olimp.presentation.util.load

class ServiceAdapter :
    ListAdapter<ServiceUI, ServiceAdapter.ServiceViewHolder>(ServiceDiffCallback()) {

    var onServiceClick: ((ServiceUI) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ServiceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = getItem(position)
        holder.itemView.setOnClickListener {
            onServiceClick?.invoke(service)
        }
        holder.bind(service)
    }

    class ServiceViewHolder(val binding: ServiceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(service: ServiceUI) {
            with(binding) {
                ivServiceIcon.load(service.imageURL, itemView.context)
                tvServiceName.text = service.name
            }
        }
    }

    class ServiceDiffCallback : DiffUtil.ItemCallback<ServiceUI>() {
        override fun areItemsTheSame(oldItem: ServiceUI, newItem: ServiceUI): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ServiceUI, newItem: ServiceUI): Boolean {
            return oldItem == newItem
        }
    }

    override fun onViewDetachedFromWindow(holder: ServiceViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Glide.with(holder.itemView.context)
            .clear(holder.binding.ivServiceIcon)
    }
}