package com.example.randomgame.repository

import com.example.randomgame.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {

    suspend fun getRandomNumber(): Int {
        return withContext(Dispatchers.IO) {
            RetrofitInstance.api.getRandomNumber()
        }
    }
}