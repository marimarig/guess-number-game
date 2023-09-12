package com.example.randomgame.repository

import com.example.randomgame.model.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    //This function is a suspend function, allowing it to be safely called from a coroutine.
    suspend fun getRandomNumber(): Int {
        return withContext(Dispatchers.IO) {

            /**
             * Inside a coroutine with IO dispatcher,
            * make a network request using RetrofitInstance.
            */
            RetrofitInstance.api.getRandomNumber()
        }
    }
}