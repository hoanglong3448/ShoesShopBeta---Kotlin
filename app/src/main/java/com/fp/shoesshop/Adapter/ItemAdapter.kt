package com.fp.shoesshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.fp.shoesshop.Model.ItemModel
import com.fp.shoesshop.databinding.ViewholderRecommendBinding

class ItemAdapter (val items : MutableList<ItemModel>):RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder (val biding: ViewholderRecommendBinding) : RecyclerView.ViewHolder(biding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        context = parent.context
        val biding =ViewholderRecommendBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(biding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.biding.brandDes.text = items[position].title
        holder.biding.brandPrice.text = "$"+items[position].price.toString()
        holder.biding.brandStar.text = items[position].rating.toString()

        val  requiresOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requiresOptions)
            .into(holder.biding.pic)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}