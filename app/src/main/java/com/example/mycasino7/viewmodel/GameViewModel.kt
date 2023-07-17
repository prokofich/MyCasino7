package com.example.mycasino7.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino7.api.Repository
import com.example.mycasino7.model.ResponseText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class GameViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextGame(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextGame()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}