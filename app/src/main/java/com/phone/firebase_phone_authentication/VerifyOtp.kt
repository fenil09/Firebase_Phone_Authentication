package com.phone.firebase_phone_authentication

import android.content.Intent
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
        val getotp1:EditText=findViewById(R.id.et2)
        val getotp2:EditText=findViewById(R.id.et3)
        val getotp3:EditText=findViewById(R.id.et4)
        val getotp4:EditText=findViewById(R.id.et5)
        val getotp5:EditText=findViewById(R.id.et6)
        val getotp6:EditText=findViewById(R.id.et7)
        val verify:Button=findViewById(R.id.button2)
        mauth= FirebaseAuth.getInstance()
        verify.setOnClickListener {
            val code1=getotp1.text.toString()
            val code2=getotp2.text.toString()
            val code3=getotp3.text.toString()
            val code4=getotp4.text.toString()
            val code5=getotp5.text.toString()
            val code6=getotp6.text.toString()
            val finalcode=code1+code2+code3+code4+code5+code6
            verifyotp(finalcode)
        }
    }

    fun verifyotp(otp:String){
        val intent=intent
        val id=intent.getStringExtra("id")
        val credential:PhoneAuthCredential=PhoneAuthProvider.getCredential(id.toString(), otp)
        mauth.signInWithCredential(credential).addOnCompleteListener(this, OnCompleteListener {
            if(it.isSuccessful){
               val intent= Intent(applicationContext,OTP_Verified_Animation::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"please check your entered otp again",Toast.LENGTH_LONG).show()
            }
        })
    }


}