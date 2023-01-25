package com.phone.firebase_phone_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerifyOtp : AppCompatActivity() {
    lateinit var mauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)
        val getotp:EditText=findViewById(R.id.et2)
        val verify:Button=findViewById(R.id.button2)
        mauth= FirebaseAuth.getInstance()
        verify.setOnClickListener {
            val code=getotp.text.toString()
            verifyotp(code)
        }
    }
    fun verifyotp(otp:String){
        val intent=intent
        val id=intent.getStringExtra("id")
        val credential:PhoneAuthCredential=PhoneAuthProvider.getCredential(id.toString(), otp)
        mauth.signInWithCredential(credential).addOnCompleteListener(this, OnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"verification completed welcome user",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"please check your entered otp again",Toast.LENGTH_LONG).show()
            }
        })
    }
}