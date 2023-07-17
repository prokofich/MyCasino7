package com.example.mycasino7.api

import com.example.mycasino7.model.DifficultText
import com.example.mycasino7.model.ResponceWebView
import com.example.mycasino7.model.ResponseText
import retrofit2.Response

class Repository {

    suspend fun setParametersPhone(phone_name:String,locale:String,unique:String): Response<ResponceWebView> {
        return RetrofitInstance.api.setPostParametersPhone(phone_name, locale, unique)
    }

    suspend fun getTextSettings(): Response<ResponseText> {
        return RetrofitInstance.api.getTextSettings()
    }

    suspend fun getTextGame(): Response<ResponseText> {
        return RetrofitInstance.api.getTextGame()
    }

    suspend fun getTextDifficult(): Response<DifficultText> {
        return RetrofitInstance.api.getTextDifficult()
    }

}