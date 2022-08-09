package com.example.stargarage

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isEmpty
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    lateinit var  auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= FirebaseAuth.getInstance()
        Sign.setOnClickListener {
            val i =Intent(this,SignUp::class.java)
            startActivity(i)

        }
        logIn.setOnClickListener {
            login()
        }


    }

    private fun login() {
        val email=username.text.toString().trim()
        val password=password.text.toString().trim()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                        val i=Intent(this,UserProfile::class.java)
                    startActivity(i)
                    Log.d(TAG, "signInWithEmail:success")


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }


    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){

            val i =Intent(this,UserProfile::class.java)
            startActivity(i)
        }
    }
}