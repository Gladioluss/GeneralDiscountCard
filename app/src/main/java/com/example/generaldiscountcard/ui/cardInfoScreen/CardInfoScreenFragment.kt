package com.example.generaldiscountcard.ui.cardInfoScreen

import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.dataClasses.Card
import com.example.generaldiscountcard.dataClasses.CardInfoObject
import com.example.generaldiscountcard.dataClasses.User
import com.example.generaldiscountcard.dataClasses.cardArrayList
import com.example.generaldiscountcard.databinding.FragmentCardInfoScreenBinding
import com.example.generaldiscountcard.utilits.CARD_LIST
import com.example.generaldiscountcard.utilits.NODE_USERS
import com.example.generaldiscountcard.utilits.REF_DATABASE_ROOT
import kotlinx.android.synthetic.main.fragment_card_info_screen.*


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

        val textViewCardShopName: TextView =
            root.findViewById<View>(R.id.card_shop_name) as TextView
        val textViewCardNumber: TextView = root.findViewById<View>(R.id.card_number) as TextView
        textViewCardShopName.text = CardInfoObject.cardInfoShopName
        textViewCardNumber.text = CardInfoObject.cardNumber.toString()

        val saveButton: Button = binding.buttonUseCard
        saveButton.setOnClickListener {
            val bottomFragment = BottomFragment()
            bottomFragment.show(parentFragmentManager, "TAG")
        }

        val deleteButton : Button = binding.buttonDelete
        deleteButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_navigation_card_info_to_navigation_wallet)
            deleteCard(User.UserName, Card(
                CardInfoObject.cardInfoShopName,
                CardInfoObject.cardNumber,
                CardInfoObject.imageIdFrontSide,
                CardInfoObject.imageIdBackSide))
        }

//        topAppBar.setNavigationOnClickListener {
//            deleteCard(User.UserName, Card(
//                CardInfoObject.cardInfoShopName,
//                CardInfoObject.cardNumber,
//                CardInfoObject.imageIdFrontSide,
//                CardInfoObject.imageIdBackSide))
//        }

//        topAppBar.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//
//                R.id.more -> {
//                    Log.d("Wallet", "Карта удалена")
//                    true
//                }
//                else -> false
//            }
//        }


        return root
    }

    fun deleteCard(userName: String, card: Card) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(userName).child(CARD_LIST).child(card.cardName)
            .removeValue()
        cardArrayList.remove(Card(
            card.cardName,
            card.cardNumber,
            card.imageIdFrontSide,
            card.imageIdBackSide))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}