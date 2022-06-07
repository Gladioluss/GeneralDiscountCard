package com.example.generaldiscountcard.ui.walletScreen

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.adapters.GridViewAdapter
import com.example.generaldiscountcard.dataClasses.CardInfoObject
import com.example.generaldiscountcard.dataClasses.cardArrayList
import com.example.generaldiscountcard.databinding.FragmentWalletScreenBinding


class WalletScreenFragment : Fragment() {

    private var _binding: FragmentWalletScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentWalletScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val view: View = inflater.inflate(R.layout.fragment_wallet_screen, container, false)

        if (cardArrayList.isEmpty()) {
            val ft: FragmentTransaction = this.requireFragmentManager().beginTransaction()
            ft.detach(this)
            ft.attach(this)
            ft.commit()
        }
        val adapter = GridViewAdapter(
            requireContext() as Activity,
            cardArrayList
        )

        val gridView = root.findViewById<View>(R.id.gridView_for_cards) as GridView
        gridView.adapter = adapter

        gridView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position).toString()

                val a = splitGridViewItem(selectedItem)
                CardInfoObject.cardInfoShopName = a[0]
                CardInfoObject.cardNumber = a[1].toBigInteger()
                CardInfoObject.imageIdFrontSide = a[2].toInt()
                CardInfoObject.imageIdBackSide = a[3].toInt()
                view.findNavController()
                    .navigate(R.id.action_navigation_wallet_to_navigation_card_info)
            }

        return root
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.top_app_bar_card_info, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.more -> {
//                deleteCard(User.UserName, Card(
//                    CardInfoObject.cardInfoShopName,
//                    CardInfoObject.cardNumber,
//                    CardInfoObject.imageIdFrontSide,
//                    CardInfoObject.imageIdBackSide))
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun splitGridViewItem(item: String): ArrayList<String> {
        val cardList = arrayListOf<String>()
        for (e in item.split('(', ')')[1].split(','))
            cardList.add(e.split('=')[1])
        return cardList
    }
}