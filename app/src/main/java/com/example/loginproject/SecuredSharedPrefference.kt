package com.example.loginproject

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecuredSharedPrefference {
    private lateinit var sharedprefference :SharedPreferences
    private const val SECURED_FILE_NAME = "encripted file"

    const val USER_EMAIL =  "useremail"
    const val USER_PASSWORD = "userpassword"
    const val IS_USER_LOGGEDIN = "isloggedin"

    fun init(context:Context){
        val masterAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedprefference = EncryptedSharedPreferences.create(
            SECURED_FILE_NAME,
            masterAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveString(key:String, value:String){
        sharedprefference.edit().putString(key,value).apply()
    }

    fun saveBoolean(key:String,value: Boolean){
        sharedprefference.edit().putBoolean(key,value).apply()
    }

    fun saveBooleanAndGetStatus(key: String, value: Boolean):Boolean{
        return sharedprefference.edit().putBoolean(key, value).commit()
    }

    fun getString(key: String):String{
        return sharedprefference.getString(key, "notfound") ?: "notfound"
    }

    fun getBoolean(key:String):Boolean{
        return sharedprefference.getBoolean(key,false)
    }

    fun clearAllPreference(){
        sharedprefference.edit().clear().apply()
    }
}