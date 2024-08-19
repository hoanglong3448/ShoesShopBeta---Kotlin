package com.fp.shoesshop.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData
import com.fp.shoesshop.Model.BrandModel
import com.fp.shoesshop.Model.ItemModel
import com.fp.shoesshop.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener

class MainViewModel():ViewModel(){

    private val firebaseDatabase = FirebaseDatabase.getInstance();
    private val _banner = MutableLiveData<List<SliderModel>>();
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    private val _item = MutableLiveData<MutableList<ItemModel>>()


    public val banners  : LiveData<List<SliderModel>> = _banner
    public val brands : LiveData<MutableList<BrandModel>> = _brand
    public val items : LiveData<MutableList<ItemModel>> = _item




    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>();
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun loadBrand(){
        val  Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>();
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

    fun loadItem(){
        val  Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>();
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(ItemModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _item.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}