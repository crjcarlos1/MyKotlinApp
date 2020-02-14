package com.cralos.mykotlinapp2.fragments.mvp.example2.presenters

import com.cralos.mykotlinapp2.fragments.mvp.example2.interactors.UsersInteractorImpl
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersInteractor
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersPresenter
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersView
import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User

class UsersPresenterImpl : UsersPresenter {

    private lateinit var view: UsersView
    private lateinit var interactor: UsersInteractor

    override fun init(view: UsersView) {
        this.view = view
        this.interactor = UsersInteractorImpl(this)
    }

    override fun getUsers() {
        view.showLoader()
        interactor.getUsers()
    }

    override fun setMessageToView(message: String) {
        view.hideLoader()
        view.showMessage(message)
    }

    override fun setUsersToView(users:List<User>) {
        view.hideLoader()
        view.showUsers(users)
    }

}