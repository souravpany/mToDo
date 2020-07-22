package com.android.own.mtodo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.own.mtodo.R
import com.android.own.mtodo.base.BaseActivity
import com.android.own.mtodo.di.component.ActivityComponent
import com.android.own.mtodo.ui.MainSharedViewModel
import com.android.own.mtodo.ui.todoadd.ToDoAddFragment
import com.android.own.mtodo.ui.todoview.ToDoViewFragment
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel>() {

    private var activeFragment: Fragment? = null

    @Inject
    lateinit var mainSharedViewModel: MainSharedViewModel

    override fun provideLayoutId(): Int = R.layout.activity_home


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        fabAdd.setOnClickListener {
            fabAdd.visibility = View.GONE
            mainSharedViewModel.onClearToDoField()
            viewModel.onToDoAddClicked()
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.todoViewFragment.observe(this, Observer {
            it.getIfNotHandled()?.run { showToDoViewFragment() }
        })

        viewModel.todoAddFragment.observe(this, Observer {
            it.getIfNotHandled()?.run { showToDoAddFragment() }
        })

        mainSharedViewModel.homeRedirection.observe(this, Observer {
            it.getIfNotHandled()?.run { onBackPressed() }
        })

        mainSharedViewModel.homeRedirectionFromView.observe(this, Observer {
            it.getIfNotHandled()?.run {
                fabAdd.visibility = View.GONE
                viewModel.onToDoAddClicked()
            }
        })
    }

    private fun showToDoViewFragment() {

        if (activeFragment is ToDoViewFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(ToDoViewFragment.TAG) as ToDoViewFragment?

        if (fragment == null) {
            fragment = ToDoViewFragment.newInstance()
            fragmentTransaction.add(R.id.fragment_container, fragment, ToDoViewFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

    private fun showToDoAddFragment() {

        if (activeFragment is ToDoAddFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(ToDoAddFragment.TAG) as ToDoAddFragment?

        if (fragment == null) {
            fragment = ToDoAddFragment.newInstance()
            fragmentTransaction.add(R.id.fragment_container, fragment, ToDoAddFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment

    }


    override fun onBackPressed() {
        if (activeFragment is ToDoAddFragment) {
            fabAdd.visibility = View.VISIBLE
            viewModel.onToDoViewClicked()
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Action bar menu item
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.aboutMeMenuItem -> {
                Log.d("HomeClass", "About is clicked..")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
