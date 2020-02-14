package com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces

import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User

interface UsersPresenter {

    fun init(view: UsersView)

    /*setDataToInteractor*/
    fun getUsers()

    /*setDataToView*/
    fun setMessageToView(message: String)

    fun setUsersToView(users:List<User>)

}