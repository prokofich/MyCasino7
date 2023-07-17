package com.example.mycasino7.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycasino7.R
import com.example.mycasino7.adapter.CardsEasyAdapter
import com.example.mycasino7.adapter.CardsHardAdapter
import com.example.mycasino7.adapter.CardsMiddleAdapter
import com.example.mycasino7.constant.*
import com.example.mycasino7.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

class GameFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    lateinit var adapterEasy: CardsEasyAdapter
    lateinit var adapterMiddle: CardsMiddleAdapter
    lateinit var adapterHard: CardsHardAdapter

    private var difficult = ""

    private var listCards = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        gameViewModel.getTextGame()
        gameViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_game_tv_pravila.text = TEXT.body()!!.text
        }

        difficult = requireArguments()[DIFFICULT].toString()
        recyclerView = id_game_rv_cards

        when(difficult){
            EASY -> {
                adapterEasy = CardsEasyAdapter(requireContext())
                recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                recyclerView.adapter = adapterEasy

                listCards = listAllCard.shuffled().slice(0..2).toMutableList() //загрузка случайных карт
                listCards.add("damapiki.jpg")
                listCards.shuffled()
            }
            MIDDLE -> {
                adapterMiddle = CardsMiddleAdapter(requireContext())
                recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
                recyclerView.adapter = adapterMiddle

                listCards = listAllCard.shuffled().slice(0..7).toMutableList() //загрузка случайных карт
                listCards.add("damapiki.jpg")
                listCards.shuffled()
            }
            HARD -> {
                adapterHard = CardsHardAdapter(requireContext())
                recyclerView.layoutManager = GridLayoutManager(requireContext(),4)
                recyclerView.adapter = adapterHard

                listCards = listAllCard.shuffled().slice(0..14).toMutableList() //загрузка случайных карт
                listCards.add("damapiki.jpg")
                listCards.shuffled()
            }
        }



        //кнопка старта игры
        id_game_button_start.setOnClickListener {
            id_game_tv_pravila.isVisible = false
            if(id_game_button_start.text=="start"){
                //начинаю игру и загружаю карты в адаптер
                id_game_button_start.text = "finish"

                when(difficult){
                    EASY -> {
                        adapterEasy.setList(listCards)
                    }
                    MIDDLE -> {
                        adapterMiddle.setList(listCards)
                    }
                    HARD -> {
                        adapterHard.setList(listCards)
                    }
                }

            }else{
                //заканчиваю игру и перехожу к результатам

                when(difficult){
                    EASY -> {
                        adapterEasy.cancelJob()
                    }
                    MIDDLE -> {
                        adapterMiddle.cancelJob()
                    }
                    HARD -> {
                        adapterHard.cancelJob()
                    }
                }

                id_game_button_start.text = "start"
                finishGame(LOSS,difficult)
            }
        }


    }

    //переход к фрагменту с результатами
    companion object{
        fun finishGame(result:String,difficult:String){
            val bundle = Bundle()
            bundle.putString(RESULT,result)
            bundle.putString(DIFFICULT,difficult)
            MAIN.navController.navigate(R.id.action_gameFragment_to_overGameFragment,bundle)
        }
    }



}