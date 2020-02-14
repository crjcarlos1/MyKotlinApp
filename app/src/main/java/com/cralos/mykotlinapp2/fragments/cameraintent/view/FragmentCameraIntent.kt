package com.cralos.mykotlinapp2.fragments.cameraintent.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cralos.mykotlinapp2.R
import com.cralos.mykotlinapp2.databinding.FragmentCameraIntentBinding
import com.cralos.mykotlinapp2.fragments.cameraintent.interfaces.OnClickFragmentCamera

class FragmentCameraIntent : Fragment(), OnClickFragmentCamera {
    companion object {
        val TAG = FragmentCameraIntent::class.java.simpleName
    }

    private val MY_PERMISSIONS_REQUEST_CAMERA_STORAGE = 102
    private val CAMERA_REQUEST_CODE = 0

    /*dataBinding*/
    private lateinit var binding: FragmentCameraIntentBinding

    override
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_camera_intent, container, false)
        initFragmentCamera()
        return binding.root
    }

    override
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Agregar permisos para el correcto funcionamineto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
            else -> {
                Toast.makeText(requireContext(), "requestCode not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    binding.imgvResult.setImageBitmap(data.extras?.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(requireContext(), "RequestCode no reconocido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override
    fun onClickTakePhoto() {
        validatePermissions()
    }

    override
    fun onClickClear() {
        binding.imgvResult.setImageResource(0)
    }

    private fun initFragmentCamera() {
        binding.onClickListener = this
    }

    private fun validatePermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_CONTACTS
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showDialogPermissions()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_CAMERA_STORAGE
                )
            }
        } else {
            openCamera()
        }

    }

    private fun showDialogPermissions() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Permisos desactivados")
        builder.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Aceptar") { dialog, which ->
            // Do something when user press the positive button
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_CAMERA_STORAGE
            )
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun openCamera() {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (callCameraIntent.resolveActivity(requireContext().packageManager) != null) // asegurarse de que hay una app que permita la captura de imagenes
        {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

}