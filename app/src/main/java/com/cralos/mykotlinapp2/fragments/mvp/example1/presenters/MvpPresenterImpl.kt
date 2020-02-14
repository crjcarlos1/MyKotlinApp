package com.cralos.mykotlinapp2.fragments.mvp.example1.presenters

import com.cralos.mykotlinapp2.fragments.mvp.example1.interactors.MvpInteractorImpl
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpInteractor
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpPresenter
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpView

class MvpPresenterImpl : MvpPresenter {

    private lateinit var view: MvpView
    private lateinit var interactor: MvpInteractor

    override fun init(view: MvpView) {
        this.view = view
        interactor = MvpInteractorImpl(this)
    }


    override fun login(email: String, password: String) {
        view.showLoader()
        interactor.login(email, password)
    }

    override fun setMessageToView(message: String) {
        view.hideLoader()
        view.showMessage(message)
    }

    override fun successLogin() {
        view.hideLoader()
        view.successLogin()
    }


}