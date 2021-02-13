package com.example.bilgiyarismasi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bilgiyarismasi.view.MainActivity2
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recycler_view.*


class recyclerView : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var auth : FirebaseAuth
    lateinit var adapter: recyclerAdapter
      var trueee :Int = 0

    private lateinit var puanlarim : Set<String>

  lateinit var examplelist:ArrayList<ExampleList>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        auth = FirebaseAuth.getInstance()

        //  dogruuu = intent.getIntExtra("dogruu", 0)

        preferences=this.getSharedPreferences("com.example.bilgiyarismasi", Context.MODE_PRIVATE)

         trueee=  preferences.getInt("dogrusayisi",0)
       // userName = preferences.getString("username","username")!!//  val exampleList =


        postToList()


  //      puanlarim = HashSet()
        //  puanlarim= preferences.getStringSet("SkorSet", HashSet<String>())!!

        //  (puanlarim as MutableSet<String>).add(dogruuu.toString())



        val layoutManager= LinearLayoutManager(this)
        recycler_view.layoutManager =layoutManager

         adapter = recyclerAdapter(examplelist)

        recycler_view.adapter = adapter
         (recycler_view.adapter as recyclerAdapter).notifyDataSetChanged()


       /* Exit.setOnClickListener {
            val intent = Intent(this, Gorsel ::class.java)
            startActivity(intent)
            finish()
        }*/
    }

private fun postToList(){
   val userName = auth.currentUser!!.displayName.toString()
    val list =ArrayList<ExampleList>()

    for(i in 1..1){

        list.add(ExampleList(" ${i}", userName,trueee.toString() ))

    }

}



}



