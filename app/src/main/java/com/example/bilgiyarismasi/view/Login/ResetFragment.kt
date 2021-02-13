package com.example.bilgiyarismasi.view.Login

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bilgiyarismasi.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_reset.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class ResetFragment : Fragment() {
    private lateinit var  auth : FirebaseAuth
    private lateinit var  email1 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        email1 = emailReset.text.toString()


        btn.setOnClickListener {
            reset(it)
        }

    }


    private fun reset(v:View){
        auth = FirebaseAuth.getInstance()
        email1 = emailReset.text.toString()

        if(email1.isEmpty()){
            emailReset.error ="Email required"
            emailReset.requestFocus()


        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
           emailReset.error ="Valid email required"
            emailReset.requestFocus()


        }
       auth.sendPasswordResetEmail(email1).addOnCompleteListener { Task ->
           if(Task.isSuccessful){
               Toast.makeText(context,"Check your email",Toast.LENGTH_LONG).show()

           }else{
               Toast.makeText(context,Task.exception!!.message, Toast.LENGTH_LONG).show()
           }
       }

    }

}