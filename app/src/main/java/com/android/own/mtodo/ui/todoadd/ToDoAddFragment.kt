package com.android.own.mtodo.ui.todoadd

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.android.own.mtodo.R
import com.android.own.mtodo.base.BaseFragment
import com.android.own.mtodo.di.component.FragmentComponent
import com.android.own.mtodo.ui.MainSharedViewModel
import com.android.own.mtodo.utils.Toaster
import kotlinx.android.synthetic.main.fragment_add_todo.*
import javax.inject.Inject

class ToDoAddFragment : BaseFragment<ToDoAddViewModel>() {

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel


    companion object {

        const val TAG: String = "ToDoAddFragment"

        fun newInstance(): ToDoAddFragment {
            val args = Bundle()
            val fragment = ToDoAddFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_add_todo


    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        edtTitle.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTitleChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        edtDescription.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onDescriptionChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })


        btnSave.setOnClickListener {

            if (btnSave.text == "Save") {
                viewModel.addToDoData()
            } else {
                viewModel.updateToDoData()
            }
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchToDoView.observe(this, Observer<Boolean> {
            it.run {
                if (this) {
                    mainSharedViewModel.newToDo.postValue(true)
                    viewModel.clearAllField()
                    mainSharedViewModel.onHomeRedirect()

                } else {
                    Toaster.show(activity!!.applicationContext, "Something went wrong..!!")
                }
            }
        })

        viewModel.titleField.observe(this, Observer {
            if (edtTitle.text.toString() != it) edtTitle.setText(it)
        })

        viewModel.descriptionField.observe(this, Observer {
            if (edtDescription.text.toString() != it) edtDescription.setText(it)
        })

        mainSharedViewModel.updateToDo.observe(this, Observer {
            viewModel.onTitleChange(it.title)
            viewModel.onDescriptionChange(it.description)
            viewModel.onIdChange(it.id)

            btnSave.text = getString(R.string.update)
        })


        mainSharedViewModel.clearField.observe(this, Observer {
            if (it) viewModel.clearAllField()
            btnSave.text = getString(R.string.save)
        })
    }
}