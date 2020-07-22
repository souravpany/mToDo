package com.android.own.mtodo.ui.intro

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.android.own.mtodo.R
import com.android.own.mtodo.base.BaseActivity
import com.android.own.mtodo.data.model.IntroSlide
import com.android.own.mtodo.di.component.ActivityComponent
import com.android.own.mtodo.ui.home.HomeActivity
import com.android.own.mtodo.ui.intro.adapter.IntroSlideAdapter
import com.android.own.mtodo.utils.Event
import kotlinx.android.synthetic.main.activity_intro.*

@Suppress("DEPRECATION")
class IntroActivity : BaseActivity<IntroViewModel>() {

    private val introSlideAdapter =
        IntroSlideAdapter(

            listOf(
                IntroSlide(
                    "TODO Creation",
                    "Hello Page One Description",
                    R.drawable.ic_food,
                    R.color.bg_screen1
                ),
                IntroSlide(
                    "Hello Page Two",
                    "Hello Page Two Description",
                    R.drawable.ic_movie,
                    R.color.bg_screen2
                ),
                IntroSlide(
                    "Hello Page Three",
                    "Hello Page Three Description",
                    R.drawable.ic_discount,
                    R.color.bg_screen3
                ),
                IntroSlide(
                    "Hello Page Four",
                    "Hello Page Four Description",
                    R.drawable.ic_travel,
                    R.color.bg_screen4
                )
            )
        )


    override fun provideLayoutId(): Int = R.layout.activity_intro


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }

    override fun setupView(savedInstanceState: Bundle?) {

        viewPager.adapter = introSlideAdapter
        setupIndicators()
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                setCurrentIndicator(position)


                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == introSlideAdapter.itemCount - 1) { // last page. make button text to GOT IT
                    btnNext.text = getString(R.string.start)
                    btnSkip.visibility = View.GONE
                } else { // still pages are left
                    btnNext.text = getString(R.string.next)
                    btnSkip.visibility = View.VISIBLE
                }
            }
        })

        btnNext.setOnClickListener {

            if (viewPager.currentItem + 1 < introSlideAdapter.itemCount) {
                viewPager.currentItem += 1
            } else {
                viewModel.lunchMainScreen()
            }

        }

        btnSkip.setOnClickListener {
            viewModel.lunchMainScreen()
        }

    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.launchHomeScreen.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        })
    }

    private fun setupIndicators() {

        val indicators = arrayOfNulls<TextView>(introSlideAdapter.itemCount)

        for (i in indicators.indices) {
            indicators[i] = TextView(this)
            indicators[i]?.text = Html.fromHtml("&#8226;")
            indicators[i]?.textSize = 35f
            layoutDots.addView(indicators[i])
        }
    }


    private fun setCurrentIndicator(index: Int) {

        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)


        val childCount = layoutDots.childCount

        for (i in 0 until childCount) {
            val textView = layoutDots[i] as TextView

            if (i == index) {
                textView.setTextColor(colorsActive[i])
            } else {
                textView.setTextColor(colorsInactive[i])
            }
        }
    }
}
