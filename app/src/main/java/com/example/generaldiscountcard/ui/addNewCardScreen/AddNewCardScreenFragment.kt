package com.example.generaldiscountcard.ui.addNewCardScreen

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.adapters.ListViewAdapter
import com.example.generaldiscountcard.dataClasses.Shop
import com.example.generaldiscountcard.databinding.FragmentAddNewCardScreenBinding


class AddNewCardScreenFragment : Fragment() {

    private var _binding: FragmentAddNewCardScreenBinding? = null
    private lateinit var shopArrayList : ArrayList<Shop>

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(AddNewCardScreenViewModel::class.java)

        _binding = FragmentAddNewCardScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val view: View = inflater.inflate(R.layout.fragment_add_new_card_screen, container, false)

        val imageId = intArrayOf(
            R.drawable.add_icon, R.drawable.add_icon, R.drawable.add_icon, R.drawable.add_icon,
            R.drawable.add_icon, R.drawable.add_icon, R.drawable.add_icon, R.drawable.add_icon

        )

        val shopName = arrayOf(
            "Добавить новую карту", "Магнит", "Верный", "Окей",
            "Samsung", "Лента", "Adidas", "Монетка"
        )

        shopArrayList = ArrayList()

        for(i in shopName.indices){
            val shop = Shop(shopName[i], imageId[i])
            shopArrayList.add(shop)
        }

        val adapter = ListViewAdapter(
            requireContext() as Activity,
            shopArrayList
        )

        val listView = root.findViewById<View>(R.id.card_list) as ListView
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

//            view.findNavController().navigate(R.id.action_navigation_add_new_card_to_navigation_fill_data_new_card)
        }

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

