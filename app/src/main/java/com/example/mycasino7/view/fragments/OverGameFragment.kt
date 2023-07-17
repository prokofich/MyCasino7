package com.example.mycasino7.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino7.R
import com.example.mycasino7.constant.*
import kotlinx.android.synthetic.main.fragment_over_game.*
import kotlinx.android.synthetic.main.fragment_settings.*

class OverGameFragment : Fragment() {

    private var result = ""
    private var difficult = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_over_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        result = requireArguments().getString(RESULT)!!
        difficult = requireArguments().getString(DIFFICULT)!!

        when(result){
            WIN -> {
                loadImage(url_image_emblem_cards,id_gameover_iv_img_cash)
                loadImage(url_image_smile_happy,id_gameover_iv_img_win_or_less)
                id_gameover_tv_win_or_loss.text = "Congratulations!you've won!"
                MAIN.setCountWin(difficult)
                id_gameover_tv_cash.text = "this is your ${MAIN.getCountWin(difficult)}th victory!"
            }
            LOSS -> {
                loadImage(url_image_emblem_cards,id_gameover_iv_img_cash)
                loadImage(url_image_smile_sad,id_gameover_iv_img_win_or_less)
                id_gameover_tv_win_or_loss.text = "unfortunately you lost"
                id_gameover_tv_cash.text = "you have ${MAIN.getCountWin(difficult)} wins in total"
            }
        }


        //загрузка логотипа игры
        loadImage(url_image_piki,id_overgame_img)

        //возврат в меню по кнопке НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_overGameFragment_to_menuFragment)
        }

        //возврат в меню по кнопке
        id_gameover_button_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_overGameFragment_to_menuFragment)
        }

        //возврат к игре с момента выбора уровня
        id_gameover_button_again.setOnClickListener {
            MAIN.navController.navigate(R.id.action_overGameFragment_to_difficultySelectionFragment)
        }

    }

    private fun loadImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }



}