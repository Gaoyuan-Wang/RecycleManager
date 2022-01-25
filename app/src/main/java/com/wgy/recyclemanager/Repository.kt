package com.wgy.recyclemanager

import androidx.lifecycle.liveData
import com.wgy.recyclemanager.model.Login
import com.wgy.recyclemanager.network.RecycleManagerNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    fun getLoginData(username: String,password: String) = liveData(Dispatchers.IO){
        val result = try {
            val loginResponse = RecycleManagerNetwork.getLoginData(username, password)
            if (loginResponse.isAccessible){
                val identity = loginResponse.identity
                Result.success(identity)
            }else{
                Result.failure(RuntimeException("Response accessibility is ${loginResponse.isAccessible}"))
            }
        }catch (e:Exception){
            Result.failure<Login>(e)
        }
        emit(result)
    }

}