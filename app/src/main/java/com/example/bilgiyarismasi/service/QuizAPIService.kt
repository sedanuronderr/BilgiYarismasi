package com.example.bilgiyarismasi.service

import com.example.bilgiyarismasi.model.Model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuizAPIService {
    //https://opentdb.com/api.php?amount=1&category=21&difficulty=medium&type=multiple
    private val BASE_URL = "https://opentdb.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(QuizAPI::class.java)


    fun getData(url:String):Single<Model>{
        return api.getQuiz(url)
    }

}