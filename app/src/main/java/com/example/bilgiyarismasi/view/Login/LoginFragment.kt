package com.example.bilgiyarismasi.view.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.bilgiyarismasi.R
import com.example.bilgiyarismasi.view.MainActivity2
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
      private lateinit var auth : FirebaseAuth
      private lateinit var email : String
      private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        email = emailLoginText.text.toString()
        password = passwordLoginText.text.toString()

        if(auth.currentUser != null){

            val intent = Intent(context,MainActivity2 ::class.java)
            startActivity(intent)
            activity?.finish()
        }

      btnSignup.setOnClickListener {
          signUp(it)
      }
        btnLogin.setOnClickListener {
            signIn()
        }
        unuttum.setOnClickListener {
            reset(it)

        }
    }
   private fun signIn(){

       email = emailLoginText.text.toString()
       password = passwordLoginText.text.toString()

       if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
           auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
               if (task.isSuccessful){
                   Toast.makeText(context,"Welcome : "+auth.currentUser!!.displayName, Toast.LENGTH_LONG).show()
                   val intent = Intent(context,
                           MainActivity2::class.java)
                   startActivity(intent)
                   activity?.finish()

               } else if (task.exception != null){
                   Toast.makeText(context,task.exception?.message, Toast.LENGTH_LONG).show()
               }
           }
       }

   }

    private fun signUp(view:View){

        val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        view.findNavController().navigate(action)

    }
    private fun reset(v:View){
        val action = LoginFragmentDirections.actionLoginFragmentToResetFragment()
        v.findNavController().navigate(action)

    }
}