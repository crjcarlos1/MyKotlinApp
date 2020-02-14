package com.cralos.mykotlinapp2.fragments.mvp.example2.interactors

import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersApi
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersInteractor
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersPresenter
import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User
import com.cralos.mykotlinapp2.fragments.mvp.example2.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersInteractorImpl(var presenter: UsersPresenter) : UsersInteractor {

    private var retrofit = RetrofitClient.retrofitInstance
    private var services = retrofit.create(UsersApi::class.java)

    override fun getUsers() {
        services.getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                presenter.setMessageToView("ERROR: ${t.message}")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                validateCode(response)
            }
        })
    }

    private fun validateCode(response: Response<List<User>>) {
        if (response.code() == 200) {
            getUsersResponse(response)
        } else {
            presenter.setMessageToView("Error responseCode: ${response.code()}")
        }
    }

    private fun getUsersResponse(response: Response<List<User>>) {
        var users = response.body()
        if (users != null && users.isNotEmpty()) {
            presenter.setUsersToView(users)
        } else {
            presenter.setMessageToView("No se encontraron usuarios")
        }
    }

}