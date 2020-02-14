package com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces

interface MvpPresenter {

    fun init(view: MvpView)

    /*set data to interactor*/
    fun login(email: String, password: String)

    /*set data to view*/
    fun setMessageToView(message: String)

    fun successLogin()
}