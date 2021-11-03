package com.example.counters.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.counters.R


object Navigate {

    fun navigateTo(fragment: Fragment, fragmentManager: FragmentManager){
        val fragmentManager: FragmentManager? = fragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}