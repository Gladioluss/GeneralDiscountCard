package com.example.generaldiscountcard.dataClasses

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.math.BigInteger

object CardInfoObject {

    var cardInfoShopName: String = ""
    var cardNumber : BigInteger = BigInteger("0")
    var imageIdFrontSide : Int = 0
    var imageIdBackSide : Int = 0
}

object User {
    var UserName: String = Firebase.auth.currentUser?.uid.toString()
}

var cardArrayList : ArrayList<Card> = ArrayList()