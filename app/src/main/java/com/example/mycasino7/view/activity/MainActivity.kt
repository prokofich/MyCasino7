package com.example.mycasino7.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino7.R
import com.example.mycasino7.constant.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    //функция получения номера цвета текста
    fun getNumberTextColor():Int{
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getInt(NUMBER_TEXT_COLOR,0)
        return preferences
    }

    //функция устаноки цвета текста(обновление)
    fun setNumbetTextColor(){
        if (getNumberTextColor()==1 || getNumberTextColor()==0){
            val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
            pref.edit()
                .putInt(NUMBER_TEXT_COLOR,2)
                .apply()
        }else{
            val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
            pref.edit()
                .putInt(NUMBER_TEXT_COLOR,1)
                .apply()
        }
    }

    //функция получения номера фона
    fun getNumberBackground():Int{
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getInt(NUMBER_BACKGROUND,0)
        return preferences
    }

    //функция установки номера фона(обновление фона)
    fun setNumberBackground(){
        if (getNumberBackground()==1 || getNumberBackground()==0){
            val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
            pref.edit()
                .putInt(NUMBER_BACKGROUND,2)
                .apply()
        }else{
            val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
            pref.edit()
                .putInt(NUMBER_BACKGROUND,1)
                .apply()
        }
    }

    //функция получения побед
    fun getCountWin(difficult:String): Int {
        when(difficult){
            EASY -> {
                return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(COUNT_WIN_EASY, 0)
            }
            MIDDLE -> {
                return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(COUNT_WIN_MIDDLE, 0)
            }
            HARD -> {
                return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(COUNT_WIN_HARD, 0)
            }
            else -> {
                return 0
            }
        }
    }

    fun setCountWin(difficult: String){
        when(difficult){
            EASY -> {
                val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getInt(COUNT_WIN_EASY,0) + 1
                val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
                pref.edit()
                    .putInt(COUNT_WIN_EASY,preferences)
                    .apply()
            }
            MIDDLE -> {
                val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getInt(COUNT_WIN_MIDDLE,0) + 1
                val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
                pref.edit()
                    .putInt(COUNT_WIN_MIDDLE,preferences)
                    .apply()
            }
            HARD -> {
                val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getInt(COUNT_WIN_HARD,0) + 1
                val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
                pref.edit()
                    .putInt(COUNT_WIN_HARD,preferences)
                    .apply()
            }
        }
    }

}