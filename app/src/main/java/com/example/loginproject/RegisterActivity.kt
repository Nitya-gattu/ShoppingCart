package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginproject.databinding.ActivityRegisterBinding
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import com.example.loginproject.userdata.GetUserRegResponse
import com.example.loginproject.userdata.UserRegInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    val apiService = ApiClient.retrofit.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registerBtn.setOnClickListener {

            addUserRegInfo()
        }
        binding.haveaccount.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun addUserRegInfo() {
        with(binding){
            val fullname = etFullName.text.toString()
            val mobileno = etMobileNo.text.toString()
            val emailid = etEmailId.text.toString()
            val password = etPassword.text.toString()

            val userinfo = UserRegInfo(
                fullName = fullname,
                mobileNo = mobileno,
                emailId = emailid,
                password = password
            )

            val call: Call<GetUserRegResponse> = apiService.addUser(userinfo)

            call.enqueue(object:Callback<GetUserRegResponse>{
                override fun onResponse(
                    call: Call<GetUserRegResponse>,
                    response: Response<GetUserRegResponse>
                ) {

                    if(response.isSuccessful){
                        Toast.makeText(this@RegisterActivity, "user is registered Successfully " , Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this@RegisterActivity, "error in  registering the user" , Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(p0: Call<GetUserRegResponse>, p1: Throwable) {
                    p1.printStackTrace()
                    Toast.makeText(this@RegisterActivity, "Unexpected error" , Toast.LENGTH_LONG).show()
                }

            })
        }

    }


}