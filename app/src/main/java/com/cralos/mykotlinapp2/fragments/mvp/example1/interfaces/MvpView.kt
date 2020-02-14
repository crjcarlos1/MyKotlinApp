package com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces

interface MvpView {
    fun showLoader()
    fun hideLoader()
    fun showMessage(message: String)
    fun successLogin()
}