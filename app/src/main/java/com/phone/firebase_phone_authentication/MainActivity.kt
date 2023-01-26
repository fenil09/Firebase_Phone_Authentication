package com.phone.firebase_phone_authentication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var mauth:FirebaseAuth
    lateinit var number:EditText
    var sessionid:String=""
    val reff:phone_otp_animation= phone_otp_animation()
    lateinit var player:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        mauth= FirebaseAuth.getInstance()
        number=findViewById(R.id.et1)
        val sendcode:Button=findViewById(R.id.button)
        sendcode.setOnClickListener {
            val phonenumber=number.text.toString()
            val verifynumber= "+91$phonenumber"
           sendverificationcode(verifynumber)
            val intent=Intent(this,phone_otp_animation::class.java)
            startActivity(intent)
        }

    }

    val mcallback=object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            sessionid=p0
            super.onCodeSent(p0, p1)
        }
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
           player= MediaPlayer.create(applicationContext,R.raw.sound1)
            player.start()
            val intent:Intent=Intent(applicationContext,VerifyOtp::class.java)
            intent.putExtra("id",sessionid)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TASK
            // helping in clearing the activity stack as we move from the old activity to the new activity so as we press the
            //back button our application gets closed as there are no activites present behind the current activity due to the above
            //flag setting code.
            startActivity(intent)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Toast.makeText(applicationContext,"$p0",Toast.LENGTH_LONG).show()
        }

    }
    fun sendverificationcode(phonenumber:String){
        val options=PhoneAuthOptions.newBuilder(mauth)
            .setPhoneNumber(phonenumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mcallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}