package com.fp.shoesshop

import  android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.fp.shoesshop.Activity.BaseActivity
import com.fp.shoesshop.Adapter.BrandAdapter
import com.fp.shoesshop.Adapter.ItemAdapter
import com.fp.shoesshop.Adapter.SliderAdapter
import com.fp.shoesshop.Model.BrandModel
import com.fp.shoesshop.Model.SliderModel
import com.fp.shoesshop.ViewModel.MainViewModel
import com.fp.shoesshop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrand()
        initItem()



    }

    private fun initBanner() {
        binding.progressBarBanner.visibility= View.VISIBLE
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }
    private fun banners(images:List<SliderModel>){
        binding.viewPagerBanner.adapter= SliderAdapter(images, binding.viewPagerBanner)
        binding.viewPagerBanner.clipToPadding=false
        binding.viewPagerBanner.clipChildren=false
        binding.viewPagerBanner.offscreenPageLimit=3
        binding.viewPagerBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerBanner.setPageTransformer(compositePageTransformer)
        if (images.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerBanner)
        }
    }

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            binding.viewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE



        })
        viewModel.loadBrand()
    }

    private fun initItem() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.items.observe(this, Observer {
            binding.viewPopular.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.viewPopular.adapter = ItemAdapter(it)
            binding.progressBarPopular.visibility = View.GONE



        })
        viewModel.loadItem()
    }
}