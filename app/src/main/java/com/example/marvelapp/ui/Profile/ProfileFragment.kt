package com.example.marvelapp.ui.Profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.base.BaseFragment
import com.example.marvelapp.data.entity.UserData
import com.example.marvelapp.databinding.FragmentProfileBinding
import com.example.marvelapp.utils.downloadFromUrl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    var profileImage : Uri? = null
    var Bitmap : Bitmap?= null

    private val storage by lazy { FirebaseStorage.getInstance() }
    private val  auth by lazy { FirebaseAuth.getInstance() }
    private val database by lazy { FirebaseFirestore.getInstance() }

    override fun onStart() {
        super.onStart()
        showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.logout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }
        binding.profileImageView.setOnClickListener {


        }

        binding.textView2.setOnClickListener {
            photoSelect()
        }
        binding.textView.setOnClickListener {
            if (binding.UserNameTV.visibility == View.VISIBLE){
                binding.UserNameTV.visibility = View.GONE
                binding.editTextTextPersonName.visibility = View.VISIBLE
            }else{
                binding.UserNameTV.visibility = View.VISIBLE

                binding.editTextTextPersonName.visibility = View.GONE

                binding.UserNameTV.text = binding.editTextTextPersonName.text
                val userName = binding.UserNameTV.text.toString()
                database.collection("user").document(auth.currentUser!!.uid).set(UserData(userName))

            }

        }

    }

    fun selectPhoto(){
        val uuid = UUID.randomUUID()
        val photonName = "${uuid}.jpg"
        val reference = storage.reference
        val imageReferance = reference.child("Post").child(photonName)
        if (profileImage !=  null){
            imageReferance.putFile(profileImage!!).addOnSuccessListener {
                val uploadedImageReference = storage.reference.child("Post").child(photonName)
                uploadedImageReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()
                    val imageHashMap = hashMapOf<String,Any>()
                    imageHashMap["imageURL"] = downloadUrl
                    database.collection("user").document(auth.currentUser!!.uid).set(UserData(userPhoto = downloadUrl )).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(requireContext(),"Photo select complete",Toast.LENGTH_LONG).show()
                        }
                    }.addOnFailureListener {exception ->
                        Toast.makeText(requireContext(),exception.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun photoSelect(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1) }
        }else{
            val storageIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(storageIntent,2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val storageIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(storageIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && requestCode == Activity.RESULT_OK && data != null){
            profileImage = data.data
            if (profileImage != null){
                val source =
                    activity?.contentResolver?.let { ImageDecoder.createSource(it, profileImage!!) }
                Bitmap = source?.let { ImageDecoder.decodeBitmap(it) }
                binding.profileImageView.setImageBitmap(Bitmap)
            }else{
                Bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver,profileImage)
                binding.profileImageView.setImageBitmap(Bitmap)
            }
        }
    }
    fun getPhoto(){
        database.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            if (it != null){
                val url = it.get("userPhoto").toString()
                binding.profileImageView.downloadFromUrl(url)
            }
        }
    }
}