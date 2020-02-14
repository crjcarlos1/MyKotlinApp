package com.cralos.mykotlinapp2.fragments.mvp.example1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cralos.mykotlinapp2.R
import com.cralos.mykotlinapp2.databinding.FragmentMvpBinding
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpPresenter
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.MvpView
import com.cralos.mykotlinapp2.fragments.mvp.example1.interfaces.OnClickLogin
import com.cralos.mykotlinapp2.fragments.mvp.example1.presenters.MvpPresenterImpl
import com.cralos.mykotlinapp2.loader.Loader

class FragmentMVP : Fragment(), OnClickLogin, MvpView {

    companion object {
        val TAG: String = FragmentMVP::class.java.simpleName
    }

    /*dataBinding*/
    private lateinit var binding: FragmentMvpBinding

    /*Presenter*/
    private lateinit var presenter: MvpPresenter

    /*Loader*/
    private lateinit var loader: Loader

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mvp, container, false)

        initFragmentMvp()
        return binding.root
    }

    override
    fun onClickLogin(email: String, password: String) {
        presenter.login(email, password)
    }

    override fun showLoader() {
        loader.show(requireFragmentManager(), Loader.TAG)
    }

    override fun hideLoader() {
        loader.dismiss()
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun successLogin() {
        showMessage("Success login")
    }

    private fun initFragmentMvp() {
        binding.onClickListener = this
        presenter = MvpPresenterImpl()
        presenter.init(this)
        loader = Loader()
    }

}