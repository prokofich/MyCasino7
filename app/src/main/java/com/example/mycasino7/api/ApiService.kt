package com.example.mycasino7.api

import com.example.mycasino7.model.DifficultText
import com.example.mycasino7.model.ResponceWebView
import com.example.mycasino7.model.ResponseText
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("splash.php")
    suspend fun setPostParametersPhone(
        @Field("phone_name") phone_name:String,
        @Field("locale") locale:String,
        @Field("unique") unique:String
    ): Response<ResponceWebView>


    @GET("14/GameText.json")
    suspend fun getTextGame():Response<ResponseText>

    @GET("14/SettingsText.json")
    suspend fun getTextSettings():Response<ResponseText>

    @GET("14/DifficultText.json")
    suspend fun getTextDifficult():Response<DifficultText>


}