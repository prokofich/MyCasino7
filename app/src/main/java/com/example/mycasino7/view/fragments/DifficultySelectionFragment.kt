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
import com.example.mycasino7.constant.*
import com.example.mycasino7.viewmodel.DifficultViewModel
import kotlinx.android.synthetic.main.fragment_difficulty_selection.*
import kotlinx.android.synthetic.main.fragment_settings.*

class DifficultySelectionFragment : Fragment() {

    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_difficulty_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка заднего фона
        when(MAIN.getNumberBackground()){
            0 -> { loadImage(url_image_background,id_difficulty_iv_img) }
            1 -> { loadImage(url_image_background,id_difficulty_iv_img) }
            2 -> { loadImage(url_image_background2,id_difficulty_iv_img) }
        }



        val difficultViewModel = ViewModelProvider(this)[DifficultViewModel::class.java]
        difficultViewModel.getTextDifficult()
        difficultViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_dif_tv_easy.text = TEXT.body()!![0].text
            id_dif_tv_middle.text = TEXT.body()!![1].text
            id_dif_tv_hard.text = TEXT.body()!![2].text
        }



        //возврат в меню
        id_dif_button_back_to_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_difficultySelectionFragment_to_menuFragment)
        }

        //возврат в меню по кнопке НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_difficultySelectionFragment_to_menuFragment)
        }

        //загрузка картинки к легкому уровню
        loadImage(url_image_easy,id_dif_iv_easy)
        loadImage(url_image_middle,id_dif_iv_middle)
        loadImage(url_image_hard,id_dif_iv_hard)

        /*
        надо сделать загрузку текстов
         */

        //переход к игре простого уровня
        id_difficult_button_easy.setOnClickListener {
            bundle.putString(DIFFICULT, EASY)
            MAIN.navController.navigate(R.id.action_difficultySelectionFragment_to_gameFragment,bundle)
        }

        //переход к игре среднего уровня
        id_difficult_button_middle.setOnClickListener {
            bundle.putString(DIFFICULT, MIDDLE)
            MAIN.navController.navigate(R.id.action_difficultySelectionFragment_to_gameFragment,bundle)
        }

        //переход к игре сложного уровня
        id_difficult_button_hard.setOnClickListener {
            bundle.putString(DIFFICULT, HARD)
            MAIN.navController.navigate(R.id.action_difficultySelectionFragment_to_gameFragment,bundle)
        }

    }

    private fun loadImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }



}