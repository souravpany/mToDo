package com.android.own.mtodo.ui.todoview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.own.mtodo.base.BaseViewModel
import com.android.own.mtodo.data.model.ToDoItem
import com.android.own.mtodo.data.repository.ToDoRepository
import com.android.own.mtodo.utils.Resource
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ToDoViewsViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val toDoRepository: ToDoRepository,
    var toDoItemList: List<ToDoItem>
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    companion object {
        const val TAG = "ToDoViewsViewModel"
    }


    val loading: MutableLiveData<Boolean> = MutableLiveData()

    val noDataFound: MutableLiveData<Boolean> = MutableLiveData()

    val allToDo = MutableLiveData<List<ToDoItem>>()

    val allToDoRefreshLists: MutableLiveData<List<ToDoItem>> = MutableLiveData()

    val deleteRefreshLists: MutableLiveData<Boolean> = MutableLiveData()


    override fun onCreate() {
        getAllToDo()
    }


    /**
     *
     * get all todoitem from room db
     *
     * */

    private fun getAllToDo() {
        loading.postValue(true)
        noDataFound.postValue(true)
        compositeDisposable.addAll(
            toDoRepository.returnAllToDo()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        loading.postValue(false)
                        toDoItemList = it
                        allToDo.postValue(it)
                        if (it.isEmpty()) {
                            noDataFound.postValue(true)
                        } else {
                            noDataFound.postValue(false)
                        }
                    },
                    {
                        noDataFound.postValue(true)
                        loading.postValue(false)
                        Log.d(TAG, it.toString())
                    }
                )
        )
    }

    /**
     *
     * refreshing todoitem list
     *
     * */

    fun refreshAllToDo() {

        loading.postValue(true)
        noDataFound.postValue(true)
        compositeDisposable.addAll(
            toDoRepository.returnAllToDo()
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        loading.postValue(false)
                        toDoItemList = it
                        allToDoRefreshLists.postValue(it)
                        if (it.isEmpty()) {
                            noDataFound.postValue(true)
                        } else {
                            noDataFound.postValue(false)
                        }
                    },
                    {
                        noDataFound.postValue(true)
                        loading.postValue(false)
                        Log.d(TAG, it.toString())
                    }
                )
        )

    }


    /**
     *
     * Deleting todoitem by it's ID
     *
     * @param id: todoitem insert id
     *
     *
     * */
    fun deleteToDoItem(id: Long) {
        compositeDisposable.addAll(
            toDoRepository.deleteToDo(id)
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        deleteRefreshLists.postValue(true)
                        messageString.postValue(Resource.error("ToDo deleted successfully"))
                    },
                    {
                        deleteRefreshLists.postValue(false)
                        messageString.postValue(Resource.error("Something went wrong"))
                        Log.d(TAG, it.toString())
                    }
                )
        )
    }
}
