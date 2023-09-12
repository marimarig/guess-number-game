package com.example.randomgame.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomgame.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    // MutableLiveData to hold the result message that will be observed by the UI.
    private val _resultMessage = MutableLiveData<String>()
    val resultMessage: LiveData<String> = _resultMessage

    // Variables to manage game state.
    private var secretNumber: Int? = null
    private var attemptsLeft = 5

    // Property to expose the remaining attempts.
    val _attemptsLeft: Int
        get() = attemptsLeft

    // Messages for different game outcomes.
    private val winMessage = "Ви виграли!"
    private val lostMessage = "Ви програли. Спробуйте ще раз"
    private val lessNumber = "Занадто мале число"
    private val biggerNumber = "Занадто велике число"

    init {
        getRandomNumber()
    }

    // Function to check the user's guess.
    fun checkGuess(guess: Int) {
        if (guess < secretNumber!!) {
            _resultMessage.value = lessNumber
        } else if (guess > secretNumber!!) {
            _resultMessage.value = biggerNumber
        } else {
            _resultMessage.value = winMessage
        }
        attemptsLeft--

        if (attemptsLeft <= 0) {
            _resultMessage.value = lostMessage
        }
    }

    // Function to start a new game, resetting attempts and generating a new secret number.
    fun startNewGame() {
        attemptsLeft = 5
        getRandomNumber()
    }

    // Function to fetch a random number from the repository.
    private fun getRandomNumber() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                secretNumber = Repository().getRandomNumber()
                Log.d("GameViewModel", "Secret number: $secretNumber")
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error: ${e.message}", e)
            }
        }
    }
}