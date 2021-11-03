package com.example.counters.utils
import android.app.AlertDialog
import android.content.Context
import com.example.counters.R
import com.alexandre.counters.ui.adapter.CounterAdapter

object AlertConection {

    fun alertDecrease(context: Context){
        val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)

        builder.setTitle(context.getString(R.string.update_internet))
        builder.setMessage("The Internet connection appears to be offline.")
        builder.setPositiveButton("Retry") { dialog, which ->
            dialog.dismiss()
        }
        builder.setNegativeButton("Dismiss") { dialog, which ->
          dialog.dismiss()
        }
        builder.show()
    }

    fun alertConfirm(context: Context, adapter: CounterAdapter, position: Int){
        val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete “Records played”?")
        builder.setPositiveButton("Delete") { dialog, which ->
           if (CounterCheckInternet.isNetworkAvailable(context)){
               adapter.deleteItem(position)
           }else{
               alertDelete(context)
           }
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    fun alertDelete(context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.delete))
        builder.setMessage("The Internet connection appears to be offline.")
        builder.setPositiveButton("ok") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    fun alertCreate(context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.create))
        builder.setMessage("The Internet connection appears to be offline.")
        builder.setPositiveButton("ok") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}