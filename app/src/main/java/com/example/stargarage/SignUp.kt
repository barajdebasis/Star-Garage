package com.example.stargarage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    private  lateinit var database:DatabaseReference
     lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth= FirebaseAuth.getInstance()

        signUpButton2.setOnClickListener {
            val i = Intent(this,Login::class.java)
            startActivity(i)

        }
        //fire base real time data store
        signUpButton1.setOnClickListener {
            val name=fullName.text.toString()
            val username=signupUname.text.toString()
            val phn1=signUpPhone.text.toString()
            val email1=signUpEmail.text.toString()
            val pass=signUpPassword.text.toString()
            if(name.isNotEmpty()&&username.isNotEmpty()&&phn1.isNotEmpty()&&email1.isNotEmpty()&&pass.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email1,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        database=FirebaseDatabase.getInstance().getReference("Users")//it will be root node

                        val user=User(name,username,email1,phn1,pass)
                        database.child(phn1).setValue(user).addOnSuccessListener {//phn will be child of Users

                            fullName.text?.clear()
                            signupUname.text?.clear()
                            signUpPhone.text?.clear()
                            signUpEmail.text?.clear()
                            signUpPassword.text?.clear()
                            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                            val i = Intent(this,Login::class.java)
                            startActivity(i)
                            finish()

                        }.addOnFailureListener {
                            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                        }

                    }
                    else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }
}