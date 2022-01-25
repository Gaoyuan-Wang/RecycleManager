package com.wgy.recyclemanager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wgy.recyclemanager.Repository
import com.wgy.recyclemanager.model.Login

class LoginViewModel: ViewModel() {
    private var loginLiveData = MutableLiveData<Login>()
    lateinit var login : Login

    val identity = Transformations.switchMap(loginLiveData){
        login -> Repository.getLoginData(login.username,login.password)
    }

    fun getLoginData(login: Login){
        loginLiveData.value = login
    }
}