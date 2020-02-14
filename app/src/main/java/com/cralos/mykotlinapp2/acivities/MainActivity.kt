package com.cralos.mykotlinapp2.acivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cralos.mykotlinapp2.R
import com.cralos.mykotlinapp2.fragments.menu.view.FragmentMenu

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragmentMenu()
    }

    override fun onBackPressed() {
        var count = supportFragmentManager.backStackEntryCount
        if (count > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    private fun showFragmentMenu() {
        var manager: FragmentManager = supportFragmentManager
        var transaction: FragmentTransaction = manager.beginTransaction()
        transaction.addToBackStack(FragmentMenu.TAG)
        var fragmentMenu =
            FragmentMenu()
        transaction.replace(R.id.containerFragments, fragmentMenu, FragmentMenu.TAG).commit()
    }

}
