package com.phone.firebase_phone_authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class phone_otp_animation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_otp_animation)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    }
}