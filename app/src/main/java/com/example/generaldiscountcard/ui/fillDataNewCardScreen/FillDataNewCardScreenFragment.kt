package com.example.generaldiscountcard.ui.fillDataNewCardScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.generaldiscountcard.R
import com.example.generaldiscountcard.dataClasses.Card
import com.example.generaldiscountcard.dataClasses.User
import com.example.generaldiscountcard.dataClasses.cardArrayList
import com.example.generaldiscountcard.databinding.FragmentFillDataNewCardBinding
import com.example.generaldiscountcard.utilits.*


class FillDataNewCardScreenFragment : Fragment() {

    private var _binding: FragmentFillDataNewCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFillDataNewCardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val buttonAddNewCard = root.findViewById<Button>(R.id.button_add_new_card)
        buttonAddNewCard.setOnClickListener {
            val cardName = root.findViewById<EditText>(R.id.input_card_name).text.toString()
            val cardNumber : String = root.findViewById<EditText>(R.id.input_card_number).text.toString()
            addNewCard(cardName, cardNumber)
        }
        return root
    }

    fun addNewCard(cardName : String, cardNumber : String){

        USER_NAME = User.UserName
        val cardImageFront = R.drawable.metro_card_image
        val cardImageBack = R.drawable.metro_card_image

        val cardDataMap = mutableMapOf<String, Any>()
        val dataMap = mutableMapOf<String, Any>()

        cardDataMap[CHILD_ID] = User.UserName

        dataMap[CARD_NAME] =  cardName
        dataMap[CARD_NUMBER] = cardNumber
        dataMap[CARD_IMAGE_FRONT] = cardImageFront
        dataMap[CARD_IMAGE_BACK] = cardImageBack

        REF_DATABASE_ROOT.child(NODE_USERS).child(USER_NAME).child(CARD_LIST).child(cardName).updateChildren(dataMap)
        REF_DATABASE_ROOT.child(NODE_USERS).child(USER_NAME).updateChildren(cardDataMap)

        cardArrayList.add(Card(cardName, cardNumber.toBigInteger(), cardImageFront, cardImageBack))

   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}