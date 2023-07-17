package com.example.mycasino7.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import com.example.mycasino7.R
import com.example.mycasino7.constant.MAIN
import com.bumptech.glide.Glide
import com.example.mycasino7.constant.url_image_background
import com.example.mycasino7.constant.url_image_background2
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //загрузка заднего фона
        when(MAIN.getNumberBackground()){
            0 -> { loadImage(url_image_background,id_menu_iv_img) }
            1 -> { loadImage(url_image_background,id_menu_iv_img) }
            2 -> { loadImage(url_image_background2,id_menu_iv_img) }
        }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //закрытие приложения по нажатии на кнопку выхода
        id_munu_exit.setOnClickListener {
            MAIN.finishAffinity()
        }

        //закрытие приложения по нажатии на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.finishAffinity()
        }

        //переход к выбору сложности игры
        id_menu_play.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_difficultySelectionFragment)
        }

        //переход в настройки игры
        id_menu_settings.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_settingsFragment)
        }



    }

    private fun loadImage(url:String,id:ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }



}