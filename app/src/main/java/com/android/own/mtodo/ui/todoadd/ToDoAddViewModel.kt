package com.android.own.mtodo.ui.todoadd

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.own.mtodo.base.BaseViewModel
import com.android.own.mtodo.data.repository.ToDoRepository
import com.android.own.mtodo.utils.Resource
import com.android.own.mtodo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ToDoAddViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val toDoRepository: ToDoRepository
) : BaseViewModel(
    schedulerProvider, compositeDisposable
) {

    companion object {
        const val TAG = "ToDoAddViewModel"
    }

    val titleField: MutableLiveData<String> = MutableLiveData()
    val descriptionField: MutableLiveData<String> = MutableLiveData()
    private val idField: MutableLiveData<Long> = MutableLiveData()
    val launchToDoView: MutableLiveData<Boolean> = MutableLiveData()


    fun onTitleChange(title: String) = titleField.postValue(title)

    fun onDescriptionChange(description: String) = descriptionField.postValue(description)

    fun onIdChange(id: Long) = idField.postValue(id)


    override fun onCreate() {

    }


    /**
     *
     * adding/inserting todoitem into room db
     *
     * */

    fun addToDoData() {

        val title = titleField.value
        val description = descriptionField.value

        if (title != "" && description != "") {

            compositeDisposable.addAll(
                toDoRepository.addToDoDataToDatabase(title!!, description!!)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            launchToDoView.postValue(true)
                            Log.d(TAG, "ToDo added successfully")
                            messageString.postValue(Resource.error("ToDo added successfully"))
                        },
                        {
                            launchToDoView.postValue(false)
                            Log.d(TAG, "Something went wrong")
                            messageString.postValue(Resource.error("Something went wrong"))
                        }
                    ))
        } else {
            messageString.postValue(Resource.error("Please Enter the data..!!"))
        }

    }


    /**
     *
     * updating todoitem into room db
     *
     * */

    fun updateToDoData() {

        val title = titleField.value
        val description = descriptionField.value
        val id = idField.value

        if (title != "" && description != "") {

            compositeDisposable.addAll(
                toDoRepository.updateToDo(id!!, title!!, description!!)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            launchToDoView.postValue(true)
                            Log.d(TAG, "ToDo updated successfully")
                            messageString.postValue(Resource.error("ToDo updated successfully"))
                        },
                        {
                            launchToDoView.postValue(false)
                            Log.d(TAG, "Something went wrong")
                            messageString.postValue(Resource.error("Something went wrong"))

                        }
                    ))
        } else {
            messageString.postValue(Resource.error("Please Enter the data..!!"))
        }

    }


    /**
     *
     * clearing field
     *
     * */

    fun clearAllField() {
        titleField.value = ""
        descriptionField.value = ""
    }
}
