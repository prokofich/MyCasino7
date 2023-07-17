package com.example.mycasino7.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycasino7.R
import com.example.mycasino7.constant.*
import com.example.mycasino7.view.fragments.GameFragment
import kotlinx.android.synthetic.main.item_card_in_rv4.view.*
import kotlinx.coroutines.*

class CardsEasyAdapter(private val context: Context): RecyclerView.Adapter<CardsEasyAdapter.CardsEasyViewHolder>() {

    class CardsEasyViewHolder(view: View):RecyclerView.ViewHolder(view)

    var listUrlImageCardsEasy = emptyList<String>()
    var countGuessedCards = 0
    var job:Job = Job()

    var flagQuens = true
    var flag3Cards = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsEasyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_in_rv4,parent,false)
        return CardsEasyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsEasyViewHolder, position: Int) {
        loadImageObratnayaStorona(holder.itemView.id_item_card4_img)
    }

    override fun getItemCount(): Int {
        return listUrlImageCardsEasy.size
    }

    override fun onViewAttachedToWindow(holder: CardsEasyViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener {
            if( flagQuens && flag3Cards){

                job = CoroutineScope(Dispatchers.Main).launch {
                    showCard(holder.adapterPosition,holder.itemView.id_item_card4_img)
                    if(checkCard(holder.adapterPosition)){
                        countGuessedCards+=1
                        if(countGuessedCards==3){
                            flag3Cards = false
                            Toast.makeText(context,"WIN!",Toast.LENGTH_SHORT).show()
                            delay(2500)
                            GameFragment.finishGame(WIN, EASY)
                        }else{
                            Toast.makeText(context,"TRUE",Toast.LENGTH_SHORT).show()
                            delay(2500)
                        }
                    }else{
                        flagQuens = false
                        Toast.makeText(context,"LOSS",Toast.LENGTH_SHORT).show()
                        delay(2500)
                        GameFragment.finishGame(LOSS,EASY)
                    }

                }

            }
        }

    }

    private fun loadImageObratnayaStorona(id:ImageView){
        Glide.with(context)
            .load(url_image_obratnaya_storona)
            .into(id)
    }


    private fun showCard(pos:Int,id: ImageView){
        Glide.with(context)
            .load(NAME_SERVER+listUrlImageCardsEasy[pos])
            .into(id)
    }

    private fun checkCard(pos: Int):Boolean{
        return mapAllCard[listUrlImageCardsEasy[pos]]!=1
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<String>){
        listUrlImageCardsEasy = list.shuffled()
        notifyDataSetChanged()
    }

    fun cancelJob(){
        if(job.isActive){
            job.cancel()
        }
    }


}