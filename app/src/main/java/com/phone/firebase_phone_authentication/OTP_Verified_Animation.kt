package com.phone.firebase_phone_authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import kotlin.concurrent.schedule

class OTP_Verified_Animation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verified_animation)
        val timer:Timer
        timer= Timer()
        timer.schedule(2000){
            val intent: Intent =Intent(applicationContext,Home::class.java)
            startActivity(intent)
            finish()
        }
    }
}