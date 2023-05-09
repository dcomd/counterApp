package com.alexandre.counters.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.counters.R
import com.alexandre.counters.ui.AddCounterFragment
import com.alexandre.counters.utils.Navigate

class ExemplosCounterAdpater (val list: List<String>, private val fragmentManagerParam: FragmentManager): RecyclerView.Adapter<ExemplosCounterAdpater.ViewHolder>(){
     private val fragmentManager: FragmentManager = fragmentManagerParam

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val textExemplo = view.findViewById<TextView>(R.id.idExemplos)

        fun bind(text: String){

            textExemplo.text = text

            textExemplo.setOnClickListener {
                Navigate.navigateTo(AddCounterFragment().apply {
                    arguments = Bundle().apply {
                        putString("EXAMPLE", textExemplo.text.toString())
                    }
                }, fragmentManager)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_exemplos, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = list[position]
        holder.bind(listItem)
    }
}