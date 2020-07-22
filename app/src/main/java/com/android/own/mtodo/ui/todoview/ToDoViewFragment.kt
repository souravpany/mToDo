package com.android.own.mtodo.ui.todoview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.own.mtodo.R
import com.android.own.mtodo.base.BaseFragment
import com.android.own.mtodo.di.component.FragmentComponent
import com.android.own.mtodo.ui.MainSharedViewModel
import com.android.own.mtodo.ui.todoview.adapter.ToDoListAdapter
import kotlinx.android.synthetic.main.fragment_view_todo.*
import javax.inject.Inject

class ToDoViewFragment : BaseFragment<ToDoViewsViewModel>() {


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var toDoListAdapter: ToDoListAdapter

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))

    private lateinit var deleteIcon: Drawable


    companion object {

        const val TAG: String = "ToDoViewFragment"

        fun newInstance(): ToDoViewFragment {
            val args = Bundle()
            val fragment = ToDoViewFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_view_todo


    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun setupView(view: View) {

        rvToDo.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = toDoListAdapter
        }

        rvToDo.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                mainSharedViewModel.onUpdateToDo(toDoListAdapter.retrieveData(position))

                mainSharedViewModel.onHomeRedirectFromView()

            }
        })

        swipeAndDeleteItem()
    }

    private fun swipeAndDeleteItem() {


        deleteIcon =
            ContextCompat.getDrawable(activity!!.applicationContext, R.drawable.ic_delete)!!


        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                viewModel.deleteToDoItem(toDoListAdapter.retrieveData(viewHolder.adapterPosition).id)
               // toDoListAdapter.removeItem(viewHolder)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                val itemView = viewHolder.itemView


                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2

                if (dX > 0) {
                    swipeBackground.setBounds(
                        itemView.left,
                        itemView.top,
                        dX.toInt(),
                        itemView.bottom
                    )

                    deleteIcon.setBounds(
                        itemView.left + iconMargin,
                        itemView.top + iconMargin,
                        itemView.left + iconMargin + deleteIcon.intrinsicHeight,
                        itemView.bottom - iconMargin
                    )
                } else {
                    swipeBackground.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )

                    deleteIcon.setBounds(
                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
                        itemView.top + iconMargin,
                        itemView.right - iconMargin,
                        itemView.bottom - iconMargin
                    )
                }

                swipeBackground.draw(c)

                c.save()


                if (dX > 0) {
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                } else {
                    c.clipRect(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                }
                deleteIcon.draw(c)

                c.restore()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rvToDo)

    }


    override fun setupObservers() {
        super.setupObservers()


        mainSharedViewModel.newToDo.observe(this, Observer {
            it.run {
                viewModel.refreshAllToDo()
            }
        })

        viewModel.deleteRefreshLists.observe(this, Observer {
            it.run {
                viewModel.refreshAllToDo()
            }
        })


        viewModel.allToDo.observe(this, Observer {
            it?.run { toDoListAdapter.appendData(this) }
        })

        viewModel.allToDoRefreshLists.observe(this, Observer {
            it?.run { toDoListAdapter.updateList(this) }
        })


        viewModel.loading.observe(this, Observer {
            pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.noDataFound.observe(this, Observer {
            if (it) {
                imageViewNoDataFound.visibility = View.VISIBLE
                noDataFoundText.visibility = View.VISIBLE
                rvToDo.visibility = View.GONE
            } else {
                rvToDo.visibility = View.VISIBLE
                imageViewNoDataFound.visibility = View.GONE
                noDataFoundText.visibility = View.GONE
            }

        })
    }
}