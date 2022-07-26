package com.tuyenhoang.appdoctruyenjsoupv2.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuyenhoang.appdoctruyenjsoupv2.R
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebase:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        firebase=Firebase.auth
        val getEmail=intent.getStringExtra("email")
        val getPass=intent.getStringExtra("pass")
        if (getEmail!=null||getPass!=null){
            binding.loginEmail.setText(getEmail)
            binding.loginPass.setText(getPass)
        }
        binding.loginTvSignup.setOnClickListener {
            val intent=Intent(applicationContext,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.loginButtonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email=binding.loginEmail.text.toString()
        val pass=binding.loginPass.text.toString()
        if (email.isEmpty()||pass.isEmpty()){
            Toast.makeText(this,"Nhập đủ thông tin!",Toast.LENGTH_SHORT).show()
            return
        }
        firebase.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show()
                val intent=Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}