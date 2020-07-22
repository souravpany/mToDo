package com.android.own.mtodo.ui.intro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.own.mtodo.R
import com.android.own.mtodo.data.model.IntroSlide

class IntroSlideAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {

        return IntroSlideViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.welcome_slide1,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {

        holder.bind(introSlides[position])
    }


    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageSlideIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val layoutBackGround = view.findViewById<RelativeLayout>(R.id.layoutBackGround)


        fun bind(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageSlideIcon.setImageResource(introSlide.icon)
            layoutBackGround.setBackgroundResource(introSlide.drawableColor)

        }
    }

}

