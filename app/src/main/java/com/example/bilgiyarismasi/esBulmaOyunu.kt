package com.example.bilgiyarismasi

import kotlinx.android.synthetic.main.activity_es_bulma_oyunu.*
import com.example.bilgiyarismasi.R.drawable.starr
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.ImageButton

import android.os.Bundle
import android.annotation.SuppressLint
import android.animation.AnimatorSet
import android.animation.AnimatorInflater
import android.content.Intent


class esBulmaOyunu : AppCompatActivity() {

    private var indexOfSingleSelectedCard: Int? = null
    private lateinit var cards: List<MemoryCard>
    private lateinit var back_anim: AnimatorSet
    private lateinit var front_anim: AnimatorSet
    private lateinit var buttons: List<ImageButton>
    var  can :Int = 5
    var scorr:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_es_bulma_oyunu)
        val images: MutableList<Int> = mutableListOf(R.drawable.camel, R.drawable.coala, R.drawable.fox,
                R.drawable.lion, R.drawable.monkey, R.drawable.wolf, R.drawable.camel, R.drawable.coala, R.drawable.fox, R.drawable.lion, R.drawable.monkey, R.drawable.wolf)


        buttons = listOf(button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12)

        images.shuffle()

        cards = buttons.indices.map { index ->  //resmi butonlara tanımladım
            MemoryCard(images[index])

        }


        buttons.forEachIndexed { index, imageButton ->// her döngü için bir index
            imageButton.setOnClickListener {

                updateModels(index)
                updateViews()


            }
        }


    }

    fun loadAnimations() {

        //  back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet
        //  front_anim = AnimatorInflater.loadAnimator(applicationContext,R.animator.front_animator) as AnimatorSet

    }

    private fun changeCameraDistance() {
// card_front.cameraDistance = 8000*scale
// card_back.cameraDistance = 8000*scale
        //  val scale:Float = applicationContext.resources.displayMetrics.density

    }

    private fun updateViews() {

        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) { //resimler eşlenince görünürlüğü
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else starr)

        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position] // cards = resimlerin olduğu yer
        if (card.isFaceUp) { // yüz yukarı değilse Kart açıksa
            // Error checking
            Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()

            return
        }


        if (indexOfSingleSelectedCard == null) {
            // 2 cards previously flipped over => restore cards + flip over the selected card   // daha önce 2 kart çevrilmiş ise
            // 1 card previously flipped over => flip over the selected card + check if the images match  // daha önce 1 car çevrilmiş ise
            // 0 cards previously flipped over => restore cards + flip over the selected card   //daha önce 0 card çevrilmiş ise
            // Three cases
            restoreCards()
            indexOfSingleSelectedCard = position // ilk bastığımız button // 2 buton basıldıktan sonraki buton

        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
          card.isFaceUp = !card.isFaceUp
    }



    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false

            }
        }}
        private fun checkForMatch(position1: Int, position2: Int) {

            if (cards[position1].identifier == cards[position2].identifier) {

                Toast.makeText(this, "Match found!!", Toast.LENGTH_SHORT).show()
                cards[position2].isMatched = true
                cards[position1].isMatched = true
              scorr++
                   if(scorr==6){
                        val puannn :Int = 5

                       val intent =Intent(this, Gorsel::class.java)
                       intent.putExtra("bonus",puannn)
                       startActivity(intent)

                   }
            }


            else{
                can--
                kalp.text= can.toString()
                if(can ==0 ){
                      for(button in buttons){
                          button.isClickable= false
                      }
                }
            }

        }


}



