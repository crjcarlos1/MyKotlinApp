package com.cralos.mykotlinapp2.fragments.mvp.example2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cralos.mykotlinapp2.R
import com.cralos.mykotlinapp2.databinding.FragmentUsersBinding
import com.cralos.mykotlinapp2.fragments.mvp.example2.adapters.UsersAdapter
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersPresenter
import com.cralos.mykotlinapp2.fragments.mvp.example2.interfaces.UsersView
import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User
import com.cralos.mykotlinapp2.fragments.mvp.example2.presenters.UsersPresenterImpl
import com.cralos.mykotlinapp2.loader.Loader

class FragmentUsers : Fragment(), UsersView {

    /*dataBinding*/
    private lateinit var binding: FragmentUsersBinding

    /*presenter*/
    private lateinit var presenter: UsersPresenter

    /*Loader*/
    private lateinit var loader: Loader

    /*adapter*/
    private lateinit var adapter: UsersAdapter

    companion object {
        val TAG = FragmentUsers::class.java.simpleName
    }

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        initFragmentUsers()
        return binding.root
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

    override fun showUsers(users: List<User>) {
        adapter = UsersAdapter(users, requireContext())
        binding.rvUsers.setHasFixedSize(false)
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter
    }

    private fun initFragmentUsers() {
        loader = Loader()
        presenter = UsersPresenterImpl()
        presenter.init(this)
        presenter.getUsers()
    }

}