package com.fp.shoesshop.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fp.shoesshop.Model.BrandModel
import com.fp.shoesshop.R
import com.fp.shoesshop.databinding.ViewholderBrandBinding

class BrandAdapter (val items :MutableList<BrandModel>):RecyclerView.Adapter<BrandAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    class Viewholder (val binding:ViewholderBrandBinding):
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition == position){
            holder.binding.pic.setBackgroundColor(0)
            holder.binding.layoutBrand.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.white)))
            holder.binding.title.visibility = View.VISIBLE
        }else{
            holder.binding.pic.setBackgroundColor(R.drawable.grey_bg)
                ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))
                holder.binding.title.visibility = View.GONE
        }


    }

    override fun getItemCount(): Int {
       return items.size
    }
}