package com.example.bilgiyarismasi.view.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.bilgiyarismasi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    private lateinit var  auth : FirebaseAuth
    private lateinit var  email1 : String
    private lateinit var password1 : String
    private lateinit var username1 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        email1 = email.text.toString()
        username1 = Username.text.toString()
        password1 = password.text.toString()

      buttonReset.setOnClickListener {
          signUp(it)
      }
    }
   private fun signUp(v:View){
       email1 = email.text.toString()
       username1 = Username.text.toString()
       password1 = password.text.toString()

       if (!email1.isNullOrEmpty() && !password1.isNullOrEmpty() && !username1.isNullOrEmpty()){

           auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener { task ->
               if (task.isSuccessful){
                   var update = UserProfileChangeRequest.Builder().setDisplayName(username1).build()
                   auth.currentUser!!.updateProfile(update)
                   val action= SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                   v.findNavController().navigate(action)
                   auth.signOut()

               }else if (task.exception != null){
                   Toast.makeText(context,task.exception!!.message, Toast.LENGTH_LONG).show()
               }
           }
       }else{
           Toast.makeText(context,"Please Write Email / User Name / Password", Toast.LENGTH_LONG).show()
       }
   }

   }
