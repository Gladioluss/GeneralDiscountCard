package com.example.generaldiscountcard.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

const val NODE_USERS = "users"
const val CARD_LIST = "card_list"
const val CHILD_ID = "id"
var USER_NAME = ""
var CARD_NAME = "name"
var CARD_NUMBER = "number"
var CARD_IMAGE_FRONT ="image_front"
var CARD_IMAGE_BACK ="image_back"


var AUTH: FirebaseAuth = FirebaseAuth.getInstance()
var REF_DATABASE_ROOT: DatabaseReference = FirebaseDatabase.getInstance().reference


//fun initFireBase(){
//    AUTH = FirebaseAuth.getInstance()
//    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
//}