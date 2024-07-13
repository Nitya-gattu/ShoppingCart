package com.example.loginproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginproject.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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
                }else {
                  savetheUserInfo(emailId,password)
                }
        }

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