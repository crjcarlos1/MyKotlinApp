package com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces

import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User

interface UsersView {
    fun showLoader()
    fun hideLoader()
    fun showMessage(message:String)
    fun showUsers(users:List<User>)
}