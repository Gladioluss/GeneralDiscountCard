package com.example.generaldiscountcard.adapters

//import com.google.firebase.database.core.view.View


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.dataClasses.Shop


class ListViewAdapter(private val context: Activity, private val arrayList: ArrayList<Shop>) :
    ArrayAdapter<Shop>(context, R.layout.card_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.card_list_item, null)

        val imageView : ImageView = view.findViewById(R.id.shop_image)
        val shopName : TextView = view.findViewById(R.id.name_of_shop)

        imageView.setImageResource(arrayList[position].imageId)
        shopName.text = arrayList[position].shopName

        return view
    }
}