package com.tuyenhoang.appdoctruyenjsoupv2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuyenhoang.appdoctruyenjsoupv2.Activities.ListStoryActivity
import com.tuyenhoang.appdoctruyenjsoupv2.ItemTopic
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ItemTopicBinding

class ItemTopicAdapter : RecyclerView.Adapter<ItemTopicAdapter.ItemTopicViewHolder> {
    private var itemTopics: MutableList<ItemTopic>

    constructor(itemTopics: MutableList<ItemTopic>) {
        this.itemTopics = itemTopics
    }

    class ItemTopicViewHolder : RecyclerView.ViewHolder {
        val binding: ItemTopicBinding

        constructor(binding: ItemTopicBinding) : super(binding.root) {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTopicViewHolder {
        return ItemTopicViewHolder(ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemTopicViewHolder, position: Int) {
        holder.binding.dataTopic=itemTopics[position]
        holder.itemView.setOnClickListener {
            setClick(holder.itemView,position)
        }

    }
    private fun setClick(itemView:View,position: Int){
        val intent=Intent(itemView.context,ListStoryActivity::class.java)
        intent.putExtra("itemTopicData",itemTopics[position])
        itemView.context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return itemTopics.size
    }
}