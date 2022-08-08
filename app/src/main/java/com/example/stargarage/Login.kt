package com.example.stargarage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isEmpty
import com.google.firebase.auth.FirebaseAuth
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

        val email=username.text.toString()
        val pass=password.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                val i =Intent(this,UserProfile::class.java)
                startActivity(i)

                Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(this,"Failed!!",Toast.LENGTH_SHORT).show()


            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser!=null){
            val i =Intent(this,UserProfile::class.java)
            startActivity(i)
        }
    }
}