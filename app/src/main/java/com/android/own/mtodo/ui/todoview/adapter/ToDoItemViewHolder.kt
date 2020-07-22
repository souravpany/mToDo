package com.android.own.mtodo.ui.todoview.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.android.own.mtodo.R
import com.android.own.mtodo.base.BaseItemViewHolder
import com.android.own.mtodo.data.model.ToDoItem
import com.android.own.mtodo.di.component.ViewHolderComponent
import kotlinx.android.synthetic.main.row_todo_details.view.*


class ToDoItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<ToDoItem, ToDoItemViewModel>(R.layout.row_todo_details, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()


        val generator = ColorGenerator.MATERIAL
        val color = generator.randomColor

        viewModel.title.observe(this, Observer {
            itemView.txtTitle.text = it

            try {
                val tDrawable = TextDrawable.builder().beginConfig()
                    .textColor(Color.WHITE)
                    .useFont(Typeface.DEFAULT)
                    .toUpperCase()
                    .endConfig()
                    .buildRound(it.substring(0, 1), color)


                itemView.imgToDoHint.setImageDrawable(tDrawable)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })


        viewModel.description.observe(this, Observer {
            itemView.txtDescription.text = it
        })


    }

    override fun setupView(view: View) {}
}