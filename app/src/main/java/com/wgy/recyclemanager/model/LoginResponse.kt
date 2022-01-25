package com.wgy.recyclemanager.model


//服务器返回的用户登录数据格式
data class LoginResponse (
    val isAccessible:Boolean,
    val identity: String
)
//向服务器请求的用户数据格式
data class Login(
    val username: String,
    val password: String
)