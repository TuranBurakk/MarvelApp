package com.example.marvelapp.ui.Profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.base.BaseFragment
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

    private val storage by lazy { FirebaseStorage.getInstance() }
    private val  auth by lazy { FirebaseAuth.getInstance() }
    private val database by lazy { FirebaseFirestore.getInstance() }

    override fun onStart() {
        super.onStart()
        showBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        binding.logout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }
        binding.profileImageView.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,100)

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
                database.collection(auth.currentUser!!.uid).document("profile").update("userName",userName)
            }

        }

        binding.setImageButton.setOnClickListener {
            val uuid = UUID.randomUUID()
            val imageName = "${uuid}.jpg"
            val refrance = storage.reference
            val imageRefrance = refrance.child("images").child(imageName)
            imageRefrance.putFile(profileImage!!).addOnSuccessListener {
                val uploadImageReferance =  storage.reference.child("images").child(imageName)
                uploadImageReferance.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrl = uri.toString()
                    database.collection(auth.currentUser!!.uid).document("profile").update("userPhoto",downloadUrl)
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100){
            profileImage = data?.data
            binding.profileImageView.setImageURI(profileImage)
        }
    }
    private fun getData(){
        database.collection(auth.currentUser!!.uid).document("profile").get().addOnSuccessListener {
            val image = it.get("userPhoto").toString()
            val userName = it.get("userName").toString()
            binding.UserNameTV.text = userName
            binding.profileImageView.downloadFromUrl(image)
        }
    }
}