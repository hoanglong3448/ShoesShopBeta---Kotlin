    package com.fp.shoesshop.Adapter

    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import androidx.recyclerview.widget.RecyclerView
    import androidx.viewpager2.widget.ViewPager2
    import com.bumptech.glide.Glide
    import com.bumptech.glide.load.resource.bitmap.CenterInside
    import com.bumptech.glide.request.RequestOptions
    import com.fp.shoesshop.Model.SliderModel
    import com.fp.shoesshop.R

    class SliderAdapter(private var sliderItems: List<SliderModel>, private val viewPager2: ViewPager2) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
        private lateinit var context: Context
        private val runnable = Runnable {
            sliderItems = sliderItems
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SliderAdapter.SliderViewHolder {
            context = parent.context
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.slider_item_container, parent, false)
            return SliderViewHolder(view)
        }

        override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
            holder.setImage(sliderItems[position], context)
            if (position == sliderItems.lastIndex) {
                viewPager2.post(runnable)
            }
        }

        override fun getItemCount(): Int {
            return sliderItems.size
        }

        class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

            fun setImage(sliderItem: SliderModel, context: Context) {
                val requestOptions = RequestOptions().transform(CenterInside())

                Glide.with(context)
                    .load(sliderItem.url)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }