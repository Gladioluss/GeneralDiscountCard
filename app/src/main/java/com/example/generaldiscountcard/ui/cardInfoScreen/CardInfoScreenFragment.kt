package com.example.generaldiscountcard.ui.cardInfoScreen

import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.dataClasses.CardInfoObject
import com.example.generaldiscountcard.databinding.FragmentCardInfoScreenBinding



class CardInfoScreenFragment : Fragment() {

    private var _binding: FragmentCardInfoScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentCardInfoScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewCardShopName: TextView = root.findViewById<View>(R.id.card_shop_name) as TextView
        val textViewCardNumber: TextView = root.findViewById<View>(R.id.card_number) as TextView
        textViewCardShopName.text = CardInfoObject.cardInfoShopName
        textViewCardNumber.text = CardInfoObject.cardNumber.toString()

        val saveButton: Button = binding.buttonUseCard
        saveButton.setOnClickListener {
            val bottomFragment = BottomFragment()
            bottomFragment.show(parentFragmentManager, "TAG")
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}