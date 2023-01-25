package com.phone.firebase_phone_authentication

import android.content.Intent
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
        }

    }

    val mcallback=object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            sessionid=p0
            super.onCodeSent(p0, p1)
        }
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            val code=p0.smsCode
            val intent:Intent=Intent(applicationContext,VerifyOtp::class.java)
            intent.putExtra("id",sessionid)
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