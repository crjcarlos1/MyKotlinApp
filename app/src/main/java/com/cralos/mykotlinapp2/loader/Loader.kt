package com.cralos.mykotlinapp2.loader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cralos.mykotlinapp2.R

class Loader : DialogFragment() {
    companion object {
        val TAG: String = Loader::class.java.simpleName
    }

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar)
    }

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.loader, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
        isCancelable = false
        return view
    }

}