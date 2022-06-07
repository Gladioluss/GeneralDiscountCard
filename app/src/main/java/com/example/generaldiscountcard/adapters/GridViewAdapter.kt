package com.example.generaldiscountcard.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.dataClasses.Card


class GridViewAdapter(private val context: Activity, private val arrayList: ArrayList<Card>) :
    ArrayAdapter<Card>(context, R.layout.card_view, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.card_view, null)

        val imageView: ImageView = view.findViewById(R.id.card_view_image_button)

        imageView.setImageResource(arrayList[position].imageIdFrontSide)

        return view
    }

    fun getCardName(position: Int): String{
        return arrayList[position].cardName
    }
}
