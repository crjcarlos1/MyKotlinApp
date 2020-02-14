package com.cralos.mykotlinapp2.fragments.mvp.example1.interactors

import android.util.Log
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.LoginApi
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpInteractor
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpPresenter
import com.cralos.mykotlinapp2.fragments.mvp.example1.models.LoginRequest
import com.cralos.mykotlinapp2.fragments.mvp.example1.models.LoginResponse
import com.cralos.mykotlinapp2.fragments.mvp.example1.retrofit.RetrofitClient
import com.cralos.mykotlinapp2.fragments.mvp.example1.retrofit.RetrofitValidations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvpInteractorImpl(var presenter: MvpPresenter) : MvpInteractor {

    private var retrofit = RetrofitClient.retrofitInstance
    private var loginApi = retrofit.create(LoginApi::class.java)

    override fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            presenter.setMessageToView("INGRESAR DATOS")
        } else {
            buildLoginRequest(email, password)
        }
    }

    private fun buildLoginRequest(email: String, password: String) {
        var request = LoginRequest(email, password)
        loginApi.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                presenter.setMessageToView(RetrofitValidations.getOnFailtureResponse(t))
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                validateCode(response)
            }
        })
    }

    private fun validateCode(response: Response<LoginResponse>) {
        if (RetrofitValidations.checkSuccessCode(response.code())) {
            getLoginResponse(response)
        } else {
            presenter.setMessageToView("Error")
        }
    }

    private fun getLoginResponse(loginResponse: Response<LoginResponse>) {
        var response = loginResponse.body()
        if (response != null) {
            var token = response.token
            Log.e("KOTLIN", "TOKEN: $token")
            presenter.successLogin()
        } else {
            presenter.setMessageToView("Error (2)")
        }
    }


}