package com.example.stargarage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfile : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        firebaseAuth= FirebaseAuth.getInstance()
        signOutButton.setOnClickListener {
            firebaseAuth.signOut()
            val i = Intent(this,Login::class.java)
            startActivity(i)
            finish()
        }
    }
}