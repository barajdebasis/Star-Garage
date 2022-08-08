package com.example.stargarage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            // window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        val topAnimation:Animation=AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val bottomAnimation:Animation=AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        imageView.startAnimation(topAnimation)
        textView.startAnimation(bottomAnimation)
        Handler(Looper.getMainLooper()).postDelayed({
            run {

                val i = Intent(this, Login::class.java)
                startActivity(i)
                finish()//if dont want that user can  do back form here


            }
        }, 6000); // Millisecond 1000 = 1 sec

    }
}




