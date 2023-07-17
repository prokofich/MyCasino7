package com.example.mycasino7.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino7.api.Repository
import com.example.mycasino7.model.DifficultText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DifficultViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<DifficultText>> = MutableLiveData()

    fun getTextDifficult(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextDifficult()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}