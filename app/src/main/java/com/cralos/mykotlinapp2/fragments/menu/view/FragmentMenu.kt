package com.cralos.mykotlinapp2.fragments.menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cralos.mykotlinapp2.R
import com.cralos.mykotlinapp2.databinding.FragmentMenuBinding
import com.cralos.mykotlinapp2.fragments.cameraintent.view.FragmentCameraIntent
import com.cralos.mykotlinapp2.fragments.menu.interfaces.OnClickMenu
import com.cralos.mykotlinapp2.fragments.mvp.example2.view.FragmentUsers

class FragmentMenu : Fragment(), OnClickMenu {

    companion object {
        val TAG: String = FragmentMenu::class.java.simpleName
    }

    /*dataBinding*/
    private lateinit var binding: FragmentMenuBinding

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        initFragmentMenu()
        return this.binding.root
    }

    override
    fun onClickMVP() {
        /*
                Ejemplo 1
                var manager = requireFragmentManager()
        var transantion = manager.beginTransaction()
        transantion.addToBackStack(FragmentMVP.TAG)
        var fragmentMvp = FragmentMVP()
        transantion.replace(R.id.containerFragments, fragmentMvp, FragmentMVP.TAG).commit()
         */

        var manager = requireFragmentManager()
        var transaction = manager.beginTransaction()
        transaction.addToBackStack(FragmentUsers.TAG)
        var fragmentUsers =
            FragmentUsers()
        transaction.replace(R.id.containerFragments, fragmentUsers, FragmentUsers.TAG).commit()
    }

    override
    fun onClickCameraIntent() {
        var manager = requireFragmentManager()
        var transaction = manager.beginTransaction()
        transaction.addToBackStack(FragmentCameraIntent.TAG)
        var fragmentCameraIntent =
            FragmentCameraIntent()
        transaction.replace(R.id.containerFragments, fragmentCameraIntent, FragmentCameraIntent.TAG).commit()
    }

    private fun initFragmentMenu() {
        binding.onClickListener = this
    }

}