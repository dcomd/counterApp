package com.alexandre.counters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.counters.R
import com.alexandre.counters.utils.Navigate

class CounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        Navigate.navigateTo(CounterInitFragment(), supportFragmentManager)
    }

}