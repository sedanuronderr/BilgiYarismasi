package com.example.bilgiyarismasi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bilgiyarismasi.view.MainActivity2
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_gorsel.*
import kotlinx.android.synthetic.main.activity_recycler_view.*


class Gorsel : AppCompatActivity()  {
    var dogruu :Int=0
    var yanliss :Int=0
    var bonusss: Int? =null
    private lateinit var preferences: SharedPreferences
    private lateinit var auth : FirebaseAuth
    lateinit var guncelKullaniciEmaili:String


    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gorsel)

        auth = FirebaseAuth.getInstance()

        preferences=this.getSharedPreferences("com.example.bilgiyarismasi", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()

        guncelKullaniciEmaili = auth.currentUser!!.displayName.toString()
        dogruu = intent.getIntExtra("dogru", 0)
        yanliss = intent.getIntExtra("yanlis", 0)



        bonusss = intent.getIntExtra("bonus", 0)
        if(bonusss != null) {

            dogruu+= bonusss!!

            bonusss = null
        }


        textView4.text = "TRUE: ${dogruu}"
        textView5.text = "FALSE: ${yanliss}"

        category.setOnClickListener {
            val intent= Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        


        /*recyclergit.setOnClickListener {
            editor.putInt("dogrusayisi", dogruu).apply()
        //    editor.putString("username",guncelKullaniciEmaili).apply()
          //  "username",guncelKullaniciEmaili
            val intent= Intent(this, recyclerView::class.java)
            // intent.putExtra("dogruu", dogruu)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)

        }

*/
      oyunbutton.setOnClickListener {
          val intent= Intent(this, esBulmaOyunu::class.java)
          startActivity(intent)


      }

    }






}

