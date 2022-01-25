package com.wgy.recyclemanager.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.wgy.recyclemanager.viewmodel.LoginViewModel
import com.wgy.recyclemanager.R
import com.wgy.recyclemanager.model.Login
import kotlinx.android.synthetic.main.login_layout.*

class LoginActivity : AppCompatActivity() {

    val PERMISSION =  arrayOf(
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )

    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, PERMISSION, 1)
        }

        //点击登录按钮
        login.setOnClickListener {
            //获取用户名与密码并发送至服务器
            viewModel.login = Login(username.text.toString(),password.text.toString())
            viewModel.getLoginData(viewModel.login)
        }

        viewModel.identity.observe(this, { result ->
            val identity = result.getOrNull()
            Toast.makeText(this,identity.toString(),Toast.LENGTH_SHORT).show()
            if (identity != null){
                username.setText("")
                password.setText("")
                intent= Intent(this,HomeActivity::class.java)
                intent.putExtra("identity",identity.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this,"用户名或密码有误，无法登录",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}