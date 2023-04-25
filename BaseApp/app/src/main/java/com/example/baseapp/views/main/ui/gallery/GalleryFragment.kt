package com.example.baseapp.views.main.ui.gallery

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.baseapp.databinding.FragmentGalleryBinding
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GalleryFragment : Fragment() {

    companion object {
        val REQUEST_IMAGE_CAPTURE = 200
        val PERMISSIONS_CODE = 100
    }

    private var _binding: FragmentGalleryBinding? = null
    lateinit var viewModel: GalleryViewModel
    lateinit var currentPhotoPath: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData(requireContext(), binding)

//        binding.btnAdd.setOnClickListener {
//            checkPermissions()
//        }
        binding.btnAdd.setOnClickListener {
            startCamera()
        }

        binding.btnSavePic.setOnClickListener {
            takePhoto()
        }

        binding.imageClose.setOnClickListener {
            binding.cardPhoto.visibility = View.GONE
        }
        binding.btnRetake.setOnClickListener {
            checkPermissions()
        }
    }

    private fun checkPermissions() {
        if (
            requireActivity().checkSelfPermission(CAMERA) == PERMISSION_GRANTED &&
            requireActivity().checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED
        ) {
            dispatchTakePictureIntent()
        } else {
            requireActivity().requestPermissions(arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE), PERMISSIONS_CODE)
        }
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = ImageCapture.Builder().build() ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("PICTURE", "Photo capture failed: ${exc.message}", exc)
                }

                override fun
                        onImageSaved(output: ImageCapture.OutputFileResults){
                    val msg = "Photo capture succeeded: ${output.savedUri}"
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                    Log.d("Message", msg)
                }
            }
        )
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview)

            } catch(exc: Exception) {
                Log.e("CAMERA_ERROR: ", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }


    fun createImageFile() : File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun dispatchTakePictureIntent() {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (e: IOException) {
                    Snackbar.make(binding.root, "Houve um erro ao tirar a sua foto!!", Snackbar.LENGTH_LONG).show()
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.baseapp.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    requireActivity().startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
//            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            when(resultCode) {

                RESULT_CANCELED -> {
                    Snackbar.make(binding.root, "Operação cancelada!!", Snackbar.LENGTH_LONG).show()
                }

                RESULT_OK -> {
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    val imageBitmap = data!!.extras!!.get("data") as Bitmap
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream)
                    val bytes = byteArrayOutputStream.toByteArray()
                    val base64 = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
                    binding.cardPhoto.visibility = View.VISIBLE
                    binding.takenPicture.setImageBitmap(imageBitmap)
                    binding.btnSave.setOnClickListener {
                        viewModel.saveImage(requireContext(), binding, findNavController(), base64)
                    }
                    // TODO
                    // Finalizar a lógica de salvamento da imagem e envio do BASE64 da imagem para a API
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_CODE) {
            if(grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED) {
                dispatchTakePictureIntent()
            } else {
                if(grantResults[0] == PERMISSION_DENIED) {
                    Toast.makeText(requireContext(), "Permissão de câmera negada!!", Toast.LENGTH_LONG).show()
                }
                if(grantResults[1] == PERMISSION_DENIED) {
                    Toast.makeText(requireContext(), "Permissão de armazenamento negada!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}