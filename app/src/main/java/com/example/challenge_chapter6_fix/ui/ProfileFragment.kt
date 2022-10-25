package com.example.challenge_chapter6_fix.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.ViewModelFactory
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.databinding.FragmentProfileBinding
import com.example.challenge_chapter6_fix.viewModel.BlurViewModel
import com.example.challenge_chapter6_fix.viewModel.UserViewModel
import com.example.challenge_chapter6_fix.workers.BlurModelFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel
    private val blurViewModel: BlurViewModel by viewModels { BlurViewModel.BlurViewModelFactory(requireActivity().application) }
    private lateinit var pref: DataUserManager
    private var image_uri: Uri? = null

    companion object {
        private val PERMISSION_CODE = 100;
        private val OUTPUT_PATH = "image_profile"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pref = DataUserManager(requireContext())
        userViewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getDataUsername().observe(viewLifecycleOwner) {
            binding.username.setText(it.toString())
        }

        userViewModel.getName().observe(viewLifecycleOwner) {
            binding.fullname.setText(it.toString())
            binding.txtFullname.setText(it.toString())
        }

        userViewModel.getEmail().observe(viewLifecycleOwner) {
            binding.txtEmail.setText(it.toString())
        }

        userViewModel.getBirthday().observe(viewLifecycleOwner) {
            binding.txtBirthday.setText(it.toString())
        }

        userViewModel.getNomor().observe(viewLifecycleOwner) {
            binding.txtNomor.setText(it.toString())
        }

        binding.btnEdit.setOnClickListener(){
            findNavController().navigate(R.id.action_profileFragment_to_updateFragment)
        }

        binding.btnBack.setOnClickListener(){
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.cardViewLogout.setOnClickListener(){
            userViewModel.setIsLogin(false)
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        takePict()
        setImageProfileBackground()

        binding.removeImage.setOnClickListener(){
            userViewModel.removeImage()
        }

        binding.blur.setOnClickListener(){
            userViewModel.saveImage(image_uri.toString())
            blurViewModel.applyBlur()
            setBlurImage()
        }

        cekImageProfile()
    }

    private fun setBlurImage() {
        val image =
            BitmapFactory.decodeFile(requireActivity().applicationContext.filesDir.path + File.separator + "blur_outputs" + File.separator + "IMG-BLURRED.png")
        binding.profileImage.setImageBitmap(image)
        saveImage()
    }

    private fun saveImage(){
        val resolver = requireActivity().applicationContext.contentResolver
        val picture = BitmapFactory.decodeStream(
            resolver.openInputStream(Uri.parse(image_uri.toString())))
        saveImageProfile(requireContext(), picture)
//        blurViewModel.applyBlur()
    }

    private fun saveImageProfile(applicationContext: Context, bitmap: Bitmap): Uri {
        val name = "img-profile.png"
        val outputDir = File(applicationContext.filesDir, "profiles")
        if (!outputDir.exists()) {
            outputDir.mkdirs() // should succeed
        }
        val outputFile = File(outputDir, name)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
        } finally {
            out?.let {
                try {
                    it.close()
                } catch (ignore: IOException) {
                }

            }
        }
        Log.d("URI_IMG", Uri.fromFile(outputFile).toString())
        return Uri.fromFile(outputFile)
    }

    private fun setImageProfileBackground() {
        if (image_uri != null){
            saveImage()
            val image = BitmapFactory.decodeFile(requireActivity().applicationContext.filesDir.path + File.separator +"profiles"+ File.separator +"img-profile.png")
            binding.profileImage.setImageBitmap(image)
        }

    }

    private fun takePict() {
        binding.uploadImage.setOnClickListener(){
            checkingPermission()
        }
    }

    private fun cekImageProfile() {
        userViewModel.getIsProfile().observe(viewLifecycleOwner){
            if (it == true){
                binding.uploadImage.visibility = View.GONE
                binding.removeImage.visibility = View.VISIBLE
            } else{
                binding.uploadImage.visibility = View.VISIBLE
                binding.removeImage.visibility = View.GONE
            }
        }
    }

    private fun checkingPermission(){
        if (isGranted(
                requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSION_CODE,
            )
        ){
            chooseImageDialog()
        }
    }

    private fun chooseImageDialog() {
        AlertDialog.Builder(context)
            .setMessage("Pilih gambar")
            .setPositiveButton("Gallery") { _, _ -> openGallery() }
            .setNegativeButton("Camera") { _, _ -> openCamera() }
            .show()
    }

    private lateinit var uri: Uri

    private fun handleCameraImage(uri: Uri) {
        Glide.with(this).load(uri).into(binding.profileImage)
        image_uri = uri
        userViewModel.saveImage(uri.toString())
    }

    private val cameraResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            if (result){
                handleCameraImage(uri)
            }
        }

    private fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        uri = FileProvider.getUriForFile(
            requireContext(),
            "${context?.packageName}.provider",
            photoFile
        )
        cameraResult.launch(uri)
    }


    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            binding.profileImage.setImageURI(result)
            image_uri = result!!
            blurViewModel.setImageUri(result)
            userViewModel.saveImage(result.toString())
        }

    private fun openGallery() {
        requireActivity().intent.type = "image/*"
        galleryResult.launch("image/*")
    }

    private fun isGranted(activity: Activity, permission: String, permissions: Array<String>, request: Int
    ): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(activity, permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else{
                ActivityCompat.requestPermissions(activity, permissions, request)
            }
            false
        }
        else{
            true
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App settings.")
            .setPositiveButton(
                "App Settings"
            ){
                _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }

}
