package com.example.generaldiscountcard.dataClasses

import java.math.BigInteger

data class Card(var cardName : String,
                var cardNumber : BigInteger,
                var imageIdFrontSide : Int,
                var imageIdBackSide : Int)
