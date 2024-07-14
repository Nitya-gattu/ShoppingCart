package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginproject.databinding.ActivityMainBinding
import com.example.loginproject.remote.ApiClient
import com.example.loginproject.remote.ApiService
import com.example.loginproject.userdata.login.GetUserLoginResponse
import com.example.loginproject.userdata.login.SendUserLoginInfo
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val apiservice:ApiService = ApiClient.retrofit.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initview()
        binding.noaccount.setOnClickListener{
            startActivity(Intent(this , RegisterActivity::class.java))
        }
    }

    private fun initview() {

        checkIfUserLoggedIn()

        binding.loginBtn.setOnClickListener{

          val emailId= validEmailId()
          val password = validEmailPassword()
                if( emailId == null || password == null){
                    //Toast.makeText(this, "emailId or Password entered is not valid", Toast.LENGTH_SHORT).show()
                    showValidationSnackbar(emailId, password)
                }else {

                    loginUser(emailId, password)


                }
        }

    }

    private fun loginUser(emailId: String, password: String) {
        val userloginInfo =   SendUserLoginInfo(
            emailId = emailId,
            password = password
        )
        val call:Call<GetUserLoginResponse> = apiservice.getifLoginInfo(userloginInfo)

        call.enqueue(object: Callback<GetUserLoginResponse>{
            override fun onResponse(
                call: Call<GetUserLoginResponse>,
                response: Response<GetUserLoginResponse>
            ) {
                if(!response.isSuccessful){
                    Toast.makeText(this@MainActivity, "some error" , Toast.LENGTH_LONG).show()
                    return
                }

                val userinfo = response.body()
                if (userinfo == null){
                    Toast.makeText(this@MainActivity, "received empty response from error" , Toast.LENGTH_LONG).show()
                    return
                }

                if(userinfo.status != 0 ){
                    Toast.makeText(this@MainActivity, userinfo.message , Toast.LENGTH_LONG).show()
                    //return
                }

                savetheUserInfo(emailId,password)



            }

            override fun onFailure(p0: Call<GetUserLoginResponse>, p1: Throwable) {
                p1.printStackTrace()
                Toast.makeText(this@MainActivity, "Unknown error" , Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showValidationSnackbar(emailId: String?, password: String?) {

        val snackbar = Snackbar.make(
            binding.main,
            R.string.message,
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction("Check"){
            if(emailId == null && password == null){
                showSnackBar("Email Id and Password is Empty")
            }else {
                if (emailId == null) {
                    showSnackBar("email Id is Null. enter vaild Email")
                }
                if (password == null) {
                    showSnackBar("Password is null. enter valid Password")
                }
            }
        }
        snackbar.show()

    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.main,
            message,
            Snackbar.LENGTH_LONG
        ).show()

    }

    private fun savetheUserInfo(emailId: String, password: String) {
        SecuredSharedPrefference.saveString(
            SecuredSharedPrefference.USER_EMAIL,
            emailId
        )

        SecuredSharedPrefference.saveString(
            SecuredSharedPrefference.USER_PASSWORD,
            password
        )

        SecuredSharedPrefference.saveBooleanAndGetStatus(
            SecuredSharedPrefference.IS_USER_LOGGEDIN,
            true
        )
        moveTODashBoardScreen()
    }

    private fun checkIfUserLoggedIn() {
        val ifUserLoggedin= SecuredSharedPrefference.getBoolean(
            SecuredSharedPrefference.IS_USER_LOGGEDIN
        )
        if(ifUserLoggedin){
            moveTODashBoardScreen()
        }
    }

    private fun moveTODashBoardScreen() {
        val intentobj= Intent(this, HomeActivity::class.java)
        //intentobj.putExtra(constant.LOGINSUCCESS, "loginsuccess")
        startActivity(intentobj)
        finish()
    }

    private fun validEmailPassword(): String? {
            val pass = binding.password.text.toString()
            if(pass.length < 6){
                return null
            }else return pass
    }

    private fun validEmailId(): String? {
       val emailid=  binding.emailId.text.toString()
        if(emailid.isEmpty()){
            return null
        }
        if(!(emailid.contains('@')) || !(emailid.endsWith(".com"))){
            return null
        }
        else return emailid
    }
}