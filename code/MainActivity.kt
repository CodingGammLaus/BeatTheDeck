package com.example.beatthedeck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.beatthedeck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cardsLeft = 52
    private val cards = arrayListOf<Int>(
        R.drawable.ace_of_clubs, R.drawable.ace_of_hearts, R.drawable.ace_of_diamonds, R.drawable.ace_of_spades,
        R.drawable.two_of_clubs, R.drawable.two_of_hearts, R.drawable.two_of_diamonds, R.drawable.two_of_spades,
        R.drawable.three_of_clubs, R.drawable.three_of_hearts, R.drawable.three_of_diamonds, R.drawable.three_of_spades,
        R.drawable.four_of_clubs, R.drawable.four_of_hearts, R.drawable.four_of_diamonds, R.drawable.four_of_spades,
        R.drawable.five_of_clubs, R.drawable.five_of_hearts, R.drawable.five_of_diamonds, R.drawable.five_of_spades,
        R.drawable.six_of_clubs, R.drawable.six_of_hearts, R.drawable.six_of_diamonds, R.drawable.six_of_spades,
        R.drawable.seven_of_clubs, R.drawable.seven_of_hearts, R.drawable.seven_of_diamonds, R.drawable.seven_of_spades,
        R.drawable.eight_of_clubs, R.drawable.eight_of_hearts, R.drawable.eight_of_diamonds, R.drawable.eight_of_spades,
        R.drawable.nine_of_clubs, R.drawable.nine_of_hearts, R.drawable.nine_of_diamonds, R.drawable.nine_of_spades,
        R.drawable.ten_of_clubs, R.drawable.ten_of_hearts, R.drawable.ten_of_diamonds, R.drawable.ten_of_spades,
        R.drawable.jack_of_clubs, R.drawable.jack_of_hearts, R.drawable.jack_of_diamonds, R.drawable.jack_of_spades,
        R.drawable.queen_of_clubs, R.drawable.queen_of_hearts, R.drawable.queen_of_diamonds, R.drawable.queen_of_spades,
        R.drawable.king_of_clubs, R.drawable.king_of_hearts, R.drawable.king_of_diamonds, R.drawable.king_of_spades,
    )

    private var cardValues = arrayListOf<Int>(
        0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    private var decksOut = arrayListOf<Boolean>(
        false, false, false, false, false, false, false, false, false
    )

    private var cardUsed = arrayListOf<Boolean>(
        false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false, false,
        false, false
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        slumpOutStart()

        initCard1()
        initCard2()
        initCard3()
        initCard4()
        initCard5()
        initCard6()
        initCard7()
        initCard8()
        initCard9()

        binding.higher.isVisible = false
        binding.lower.isVisible = false
    }

    private fun slumpOutStart() {

        var card = slumpNextCard()
        cardValues[0] = card
        binding.card1.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[1] = card
        binding.card2.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[2] = card
        binding.card3.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[3] = card
        binding.card4.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[4] = card
        binding.card5.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[5] = card
        binding.card6.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[6] = card
        binding.card7.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[7] = card
        binding.card8.setImageResource(cards[card])

        card = slumpNextCard()
        cardValues[8] = card
        binding.card9.setImageResource(cards[card])
    }

    private fun disableCards() {

        binding.card1.isEnabled = false
        binding.card2.isEnabled = false
        binding.card3.isEnabled = false
        binding.card4.isEnabled = false
        binding.card5.isEnabled = false
        binding.card6.isEnabled = false
        binding.card7.isEnabled = false
        binding.card8.isEnabled = false
        binding.card9.isEnabled = false
        binding.card1.alpha = 0.3F
        binding.card2.alpha = 0.3F
        binding.card3.alpha = 0.3F
        binding.card4.alpha = 0.3F
        binding.card5.alpha = 0.3F
        binding.card6.alpha = 0.3F
        binding.card7.alpha = 0.3F
        binding.card8.alpha = 0.3F
        binding.card9.alpha = 0.3F
        binding.higher.isVisible = true
        binding.lower.isVisible = true
    }

    private fun enableCards() {

        binding.card1.isEnabled = true
        binding.card2.isEnabled = true
        binding.card3.isEnabled = true
        binding.card4.isEnabled = true
        binding.card5.isEnabled = true
        binding.card6.isEnabled = true
        binding.card7.isEnabled = true
        binding.card8.isEnabled = true
        binding.card9.isEnabled = true
        binding.card1.alpha = 1F
        binding.card2.alpha = 1F
        binding.card3.alpha = 1F
        binding.card4.alpha = 1F
        binding.card5.alpha = 1F
        binding.card6.alpha = 1F
        binding.card7.alpha = 1F
        binding.card8.alpha = 1F
        binding.card9.alpha = 1F
        binding.higher.isVisible = false
        binding.lower.isVisible = false
    }

    /**
     *
     */
    private fun slumpNextCard(): Int {

        cardsLeft--
        binding.cardsLeft.text = "Cards left: " + cardsLeft

        while(true) {

            val card = (0..51).random()

            if(!cardUsed[card]) {

                cardUsed[card] = true
                return card
            }
        }
    }

    /**
     *
     */
    private fun initCard1() {

        binding.card1.setOnClickListener() {

            disableCards()
            binding.card1.alpha = 1F
            binding.card1.isEnabled = true

            binding.higher.setOnClickListener() {

                val card = slumpNextCard()

                if(card > cardValues[0]) {
                    binding.mainText.text = "Correct!"
                    cardValues[0] = card
                    binding.card1.setImageResource(cards[card])
                    initCard1()
                    checkIfWon()
                }

                else {
                    binding.mainText.text = "Wrong!"
                    decksOut[0] = true
                }

                enableCards()
                checkDecksOut()
            }

            binding.lower.setOnClickListener() {

                val card = slumpNextCard()

                if(card < cardValues[0]) {
                    binding.mainText.text = "Correct!"
                    cardValues[0] = card
                    binding.card1.setImageResource(cards[card])
                    initCard1()
                    checkIfWon()
                }

                else {
                    binding.mainText.text = "Wrong!"
                    decksOut[0] = true
                }

                enableCards()
                checkDecksOut()
            }

            binding.card1.setOnClickListener() {

                enableCards()
                initCard1()
                checkDecksOut()
            }
        }
    }

    /**
     *
     */
    private fun initCard2() {

        binding.card2.setOnClickListener() {

            disableCards()
            binding.card2.alpha = 1F
            binding.card2.isEnabled = true

            binding.higher.setOnClickListener() {

                val card = slumpNextCard()

                if(card > cardValues[1]) {
                    binding.mainText.text = "Correct!"
                    cardValues[1] = card
                    binding.card2.setImageResource(cards[card])
                    initCard2()
                    checkIfWon()
                }

                else {
                    binding.mainText.text = "Wrong!"
                    decksOut[1] = true
                }

                enableCards()
                checkDecksOut()
            }

            binding.lower.setOnClickListener() {

                val card = slumpNextCard()

                if(card < cardValues[1]) {
                    binding.mainText.text = "Correct!"
                    cardValues[1] = card
                    binding.card2.setImageResource(cards[card])
                    initCard2()
                    checkIfWon()
                }

                else {
                    binding.mainText.text = "Wrong!"
                    decksOut[1] = true
                }

                enableCards()
                checkDecksOut()
            }

            binding.card2.setOnClickListener() {

                enableCards()
                initCard2()
                checkDecksOut()
            }
        }
    }

    /**
     *
     */
    private fun initCard3() {

        binding.card3.setOnClickListener() {

            disableCards()
            binding.card3.alpha = 1F
            binding.card3.isEnabled = true

            binding.higher.setOnClickListener() {

                val card = slumpNextCard()

                if(card > cardValues[2]) {
                    binding.mainText.text = "Correct!"
                    cardValues[2] = card
                    binding.card3.setImageResource(cards[card])
                    initCard3()
                    checkIfWon()
                }

                else {
                    binding.mainText.text = "Wrong!"
                    decksOut[2] = true
                }

                enableCards()
                checkDecksOut()
            }

            binding.lower.setOnClickListener() {

                val card = slumpNextCard()

                if(card < cardValues[2]) {
                    binding.mainText.text = "Correct!"
                    cardValues[2] = card
                    binding.card1.setImageResource(cards[card])
                    initCard3()
                    checkIfWon()
                }

                else {
                    binding.mainText.text = "Wrong!"
                    decksOut[2] = true
                }

                enableCards()
                checkDecksOut()
            }

            binding.card3.setOnClickListener() {

                enableCards()
                initCard3()
                checkDecksOut()
            }
        }
    }

    /**
     *
     */
    private fun initCard4() {

        binding.card4.setOnClickListener() {

            val card = slumpNextCard()
            binding.card4.setImageResource(cards[card])
        }
    }

    /**
     *
     */
    private fun initCard5() {

        binding.card5.setOnClickListener() {

            val card = slumpNextCard()
            binding.card5.setImageResource(cards[card])
        }
    }

    /**
     *
     */
    private fun initCard6() {

        binding.card6.setOnClickListener() {

            val card = slumpNextCard()
            binding.card6.setImageResource(cards[card])
        }
    }

    /**
     *
     */
    private fun initCard7() {

        binding.card7.setOnClickListener() {

            val card = slumpNextCard()
            binding.card7.setImageResource(cards[card])
        }
    }

    /**
     *
     */
    private fun initCard8() {

        binding.card8.setOnClickListener() {

            val card = slumpNextCard()
            binding.card8.setImageResource(cards[card])
        }
    }

    /**
     *
     */
    private fun initCard9() {

        binding.card9.setOnClickListener() {

            val card = slumpNextCard()
            binding.card9.setImageResource(cards[card])
        }
    }

    private fun checkDecksOut() {

        if(decksOut[0]) {

            binding.card1.alpha = 0.1F
            binding.card1.isEnabled = false
        }

        if(decksOut[1]) {

            binding.card2.alpha = 0.1F
            binding.card2.isEnabled = false
        }

        if(decksOut[2]) {

            binding.card3.alpha = 0.1F
            binding.card3.isEnabled = false
        }

        if(decksOut[3]) {

            binding.card4.alpha = 0.1F
            binding.card4.isEnabled = false
        }

        if(decksOut[4]) {

            binding.card5.alpha = 0.1F
            binding.card5.isEnabled = false
        }

        if(decksOut[5]) {

            binding.card6.alpha = 0.1F
            binding.card6.isEnabled = false
        }

        if(decksOut[6]) {

            binding.card7.alpha = 0.1F
            binding.card7.isEnabled = false
        }

        if(decksOut[7]) {

            binding.card8.alpha = 0.1F
            binding.card8.isEnabled = false
        }

        if(decksOut[8]) {

            binding.card9.alpha = 0.1F
            binding.card9.isEnabled = false
        }
    }

    /**
     *
     */
    private fun checkIfWon() {

        if(cardsLeft == 0) {

            binding.mainText.text = "Won!"
        }
    }
}