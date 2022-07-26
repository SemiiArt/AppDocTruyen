package com.tuyenhoang.appdoctruyenjsoupv2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tuyenhoang.appdoctruyenjsoupv2.Activities.StoryActivity
import com.tuyenhoang.appdoctruyenjsoupv2.ItemStory
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ItemStoryBinding

class ItemStoryAdapter:RecyclerView.Adapter<ItemStoryAdapter.ItemStoryViewHoler> {
    private var listItemStory:MutableList<ItemStory>
    constructor(listItemStory:MutableList<ItemStory>){
        this.listItemStory=listItemStory
    }
    class ItemStoryViewHoler:RecyclerView.ViewHolder{
        val binding:ItemStoryBinding
        constructor(binding: ItemStoryBinding):super(binding.root){
            this.binding=binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStoryViewHoler {
        return ItemStoryViewHoler(ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemStoryViewHoler, position: Int) {
        holder.binding.itemStory=listItemStory[position]
        holder.itemView.setOnClickListener {
            setClick(holder.itemView,position)
        }
    }
    private fun setClick(itemView:View,position: Int){
        val intent=Intent(itemView.context,StoryActivity::class.java)
        intent.putExtra("itemStoryData",listItemStory[position])
        itemView.context.startActivity(intent)
    }
    override fun getItemCount(): Int {
        return listItemStory.size
    }
}