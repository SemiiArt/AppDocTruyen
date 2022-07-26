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
import com.tuyenhoang.appdoctruyenjsoupv2.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebase:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        firebase=Firebase.auth
        binding.signupTvLogin.setOnClickListener {
            val intent=Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.signupButtonSignup.setOnClickListener {
            signup()
        }
    }

    private fun signup() {
        val email=binding.signupEmail.text.toString()
        val pass=binding.signupPass.text.toString()
        val confirmPass=binding.signupConfPass.text.toString()
        if (email.isEmpty()||pass.isEmpty()||confirmPass.isEmpty()){
            Toast.makeText(this,"Nhập đủ thông tin",Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirmPass){
            Toast.makeText(this,"Nhập sai mật khẩu!",Toast.LENGTH_SHORT).show()
            return
        }
        firebase.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show()
                val intent=Intent(applicationContext,LoginActivity::class.java)
                intent.putExtra("email",email)
                intent.putExtra("pass",pass)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Đăng ký thất bại!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}