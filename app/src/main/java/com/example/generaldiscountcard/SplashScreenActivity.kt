package com.example.generaldiscountcard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.generaldiscountcard.dataClasses.Card
import com.example.generaldiscountcard.dataClasses.User
import com.example.generaldiscountcard.dataClasses.cardArrayList
import com.example.generaldiscountcard.utilits.*
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_splash_screen.*


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity  : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (Firebase.auth.currentUser == null) {
            signIn()
        } else {
            if (cardArrayList.isEmpty())
            {
                loadCardsFromFireBase(User.UserName)
                splash_screen_image.alpha = 0f
                splash_screen_image.animate().setDuration(2000).alpha(1f).withEndAction{

                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
            else{
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }


    fun loadCardsFromFireBase(userName: String) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(userName).child(CARD_LIST).get()
            .addOnSuccessListener {
                for (e in it.children)
                {
                    val cardName = e.child(CARD_NAME).value.toString()
                    val cardNumber = e.child(CARD_NUMBER).value.toString().toBigInteger()
                    val cardImageFront = e.child(CARD_IMAGE_FRONT).value.toString().toInt()
                    val cardImageBack = e.child(CARD_IMAGE_BACK).value.toString().toInt()
                    cardArrayList.add(Card(cardName, cardNumber, cardImageFront, cardImageBack))
                }
            }
    }

    fun signIn() {
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    fun signOut() {
//        AuthUI.getInstance()
//            .signOut(this)
//            .addOnCompleteListener {
//                // ...
//            }
        Firebase.auth.signOut()
        signIn()
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
        } else {
            println(response?.error?.message)
        }
    }
}