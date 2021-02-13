package com.example.bilgiyarismasi.view



import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bilgiyarismasi.Gorsel
import com.example.bilgiyarismasi.R
import com.example.bilgiyarismasi.model.Results
import com.example.bilgiyarismasi.viewmodel.QuizzShowViewModel
import kotlinx.android.synthetic.main.fragment_quizz_show.*
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DUPLICATE_LABEL_IN_WHEN")
open class QuizzShow : Fragment() {
    var myArray = ArrayList<String>()
    private lateinit var preferences: SharedPreferences
    private lateinit var time: CountDownTimer
    private lateinit var viewModel: QuizzShowViewModel
    private var a1 = ""
    private var a2 = ""
    private var a3 = ""
    private var a4 = ""
    var tdcount =0
    var tycount =0

    private lateinit var num2 :View
    private lateinit var data: List<Results>
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizz_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(context, R.raw.music)
        mediaPlayer?.start()


        val db = context?.getSharedPreferences("com.example.bilgiyarismasi", Context.MODE_PRIVATE)
        val URL = db?.getString("url", "Fail")

        viewModel = ViewModelProviders.of(this).get(QuizzShowViewModel::class.java)
        viewModel.refreshData(URL!!)

        observeLiveData()
        animasyonbasla()


        
        abtn.setOnClickListener {

            checkIt(a1, it, abtn, it.context, this, URL)

            bbtn.setBackgroundResource(R.drawable.custom_btn)
            cbtn.setBackgroundResource(R.drawable.custom_btn)
            dbtn.setBackgroundResource(R.drawable.custom_btn)



        }


       bbtn.setOnClickListener {
            checkIt(a1, it, bbtn, it.context, this, URL)

            abtn.setBackgroundResource(R.drawable.custom_btn)
            cbtn.setBackgroundResource(R.drawable.custom_btn)
            dbtn.setBackgroundResource(R.drawable.custom_btn)
        }


        cbtn.setOnClickListener {
            checkIt(a1, it, cbtn, it.context, this, URL)

            abtn.setBackgroundResource(R.drawable.custom_btn)
            bbtn.setBackgroundResource(R.drawable.custom_btn)

            dbtn.setBackgroundResource(R.drawable.custom_btn)
        }


        dbtn.setOnClickListener {

            checkIt(a1, it, dbtn, it.context, this, URL)

            abtn.setBackgroundResource(R.drawable.custom_btn)
            bbtn.setBackgroundResource(R.drawable.custom_btn)
            cbtn.setBackgroundResource(R.drawable.custom_btn)
            }



        pasGec.setOnClickListener {
            viewModel = ViewModelProviders.of(this).get(QuizzShowViewModel::class.java)
            viewModel.refreshData(URL!!)

            pasGec.isClickable = false
            pasGec.setBackgroundResource(R.drawable.ic_baseline_clear_24)
            
        }



          sikcikar.setOnClickListener {
              sikcikar.isClickable = false
              sikcikar.setBackgroundResource(R.drawable.ic_baseline_clear_24)

              if(a1 == abtn.text.toString() ){

                  cbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                  dbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)


              }

              if(a1 == bbtn.text.toString()){

                  abtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                  dbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)

              }
              if(a1 == cbtn.text.toString()){

                  abtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                  bbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)


              }
              if(a1 ==dbtn.text.toString()){
                  abtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)

                  bbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
              }



          }

           Dogrucevap.setOnClickListener {

               Dogrucevap.isClickable = false
               Dogrucevap.setBackgroundResource(R.drawable.ic_baseline_clear_24)


               if(a1 == abtn.text.toString() ){
                   bbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   cbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   dbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)


               }

               if(a1 == bbtn.text.toString()){
                   cbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   abtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   dbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)

               }
               if(a1 == cbtn.text.toString()){

                   abtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   bbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   dbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)

               }
               if(a1 ==dbtn.text.toString()){
                   abtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   cbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)
                   bbtn.setBackgroundResource(R.drawable.ic_baseline_clear_24)

               }

           }


    }


    fun observeLiveData() {
        viewModel.questionData.observe(viewLifecycleOwner, Observer { question ->
            question?.let {

                data = it.results

                textView2.setText(data[0].question)

                a1 = data[0].correct_answer
                println(a1)
                a2 = data[0].incorrect_answers[0]
                a3 = data[0].incorrect_answers[1]
                a4 = data[0].incorrect_answers[2]

                myArray = ArrayList()
                myArray.add(a1)
                myArray.add(a3)
                myArray.add(a2)
                myArray.add(a4)

                Collections.shuffle(myArray)

                abtn.setText(myArray[3])
                bbtn.setText(myArray[0])
                cbtn.setText(myArray[1])
                dbtn.setText(myArray[2])

                scrollView3.visibility = View.VISIBLE
                errorShow.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE

            }


        })





         viewModel.questionLoading.observe(viewLifecycleOwner, Observer { loading ->
             loading?.let {
                 if (it) {
                     scrollView3.visibility = View.INVISIBLE
                     errorShow.visibility = View.INVISIBLE
                     progressBar.visibility = View.VISIBLE
                 }
             }
         })
        viewModel.questionError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    scrollView3.visibility = View.INVISIBLE
                    errorShow.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                }

            }
        })

    }




    fun animasyonbasla(){


        btnSayac.animate().scaleX(0f).duration=10000
        time= object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {


            }

            override fun onFinish() {
                btnSayac.scaleX = 1f
                time.start()
                btnSayac.animate().scaleX(0f).duration = 10000


               val intent =Intent(context, Gorsel::class.java)
                intent.putExtra("dogru", tdcount)

                intent.putExtra("yanlis", tycount)
                startActivity(intent)
                }


        }.start()
    }



    fun checkIt(correct: String, v: View, btn: Button, c: Context, f: Fragment, url: String){


        if(correct == btn.text.toString()){
            tdcount++


            val toast = Toast.makeText(c, "DOĞRU BİLDİNİZ..", Toast.LENGTH_SHORT);
            val view = toast.getView()
            toast.setGravity(Gravity.LEFT, 200, 250)

            if (view != null) {
                view.setBackgroundColor(Color.rgb(115, 0, 153))
                view.setBackgroundResource(R.drawable.button_background)


            }


            toast.show()


            var viewModel = ViewModelProviders.of(f).get(QuizzShowViewModel::class.java)
            viewModel.refreshData(url)


        }


        else  {

            tycount++


            val toast = Toast.makeText(c, "DOĞRU BİLEMEDİNİZ..", Toast.LENGTH_SHORT);
            val view = toast.getView()
            toast.setGravity(Gravity.LEFT, 200, 250)
            //To change the Background of Toast

            if (view != null) {
                view.setBackgroundColor(Color.rgb(115, 0, 153))
                view.setBackgroundResource(R.drawable.background)
            }
            toast.show()



            var viewModel = ViewModelProviders.of(f).get(QuizzShowViewModel::class.java)
            viewModel.refreshData(url)


        }




    }






}




