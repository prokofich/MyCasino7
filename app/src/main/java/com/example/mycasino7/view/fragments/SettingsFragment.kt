package com.example.mycasino7.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino7.R
import com.example.mycasino7.constant.MAIN
import com.example.mycasino7.constant.url_image_background
import com.example.mycasino7.constant.url_image_background2
import com.example.mycasino7.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка заднего фона
        when(MAIN.getNumberBackground()){
            0 -> { loadImage(url_image_background,id_set_iv_img) }
            1 -> { loadImage(url_image_background,id_set_iv_img) }
            2 -> { loadImage(url_image_background2,id_set_iv_img) }
        }



        val settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        settingsViewModel.getTextSettings()
        settingsViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_set_tv_rules.text = TEXT.body()!!.text
        }



        //возврат в меню по кнопке
        id_set_button_back_to_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_menuFragment)
        }

        //возврат в меню по кнопке НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_settingsFragment_to_menuFragment)
        }

        /*
        нужно добавить смену фона и цвета текста
         */

        //смена заднего фона
        id_set_button_change_image_back.setOnClickListener {
            MAIN.setNumberBackground()
            when(MAIN.getNumberBackground()){
                0 -> { loadImage(url_image_background,id_set_iv_img) }
                1 -> { loadImage(url_image_background,id_set_iv_img) }
                2 -> { loadImage(url_image_background2,id_set_iv_img) }
            }
        }






    }

    private fun loadImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }



}