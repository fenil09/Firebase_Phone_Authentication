package com.phone.firebase_phone_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Toast.makeText(this,"Welcome user",Toast.LENGTH_SHORT).show()
    }
}