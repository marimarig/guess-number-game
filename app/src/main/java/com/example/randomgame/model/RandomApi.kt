package com.example.randomgame.model

import retrofit2.http.GET

/**
* The RandomApi interface defines a method for performing an HTTP GET request
* to get a random number from the Random.org server.
*/

interface RandomApi {

    /**
    Makes a GET request to the Random.org server to retrieve a single random number.
    * return a random number from 1 to 100 inclusive
    */
    @GET("integers/?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new")
    suspend fun getRandomNumber(): Int
}