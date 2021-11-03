package com.example.counters.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


object ShareComponent {

    fun share(context: Context, counterName: String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, counterName)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(context, shareIntent, null)
    }
}