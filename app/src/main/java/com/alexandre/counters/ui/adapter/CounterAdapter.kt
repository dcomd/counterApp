package com.alexandre.counters.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.counters.R
import com.alexandre.counters.domain.model.Counters
import com.example.counters.utils.AlertConection
import com.example.counters.utils.CounterCheckInternet
import com.alexandre.counters.presentation.viewModel.CounterViewModel
import java.util.*


class CounterAdapter(private val list : MutableList<Counters>, private val viewModel: CounterViewModel) :
    RecyclerView.Adapter<CounterAdapter.CounterViewHolder>(), Filterable {

    private var counterFisrtList =  list
    private var selectedItemPosition: Int? = null
    var onClickItem: ((Int)->Unit)? = null



    inner class CounterViewHolder(view:View): RecyclerView.ViewHolder(view){
        private val textCounter: TextView = view.findViewById(R.id.txtOrder)
        private val textNumber : TextView = view.findViewById(R.id.item_chlid_num)
        private val buttonDecrease : ImageButton = view.findViewById(R.id.item_decr)
        private val buttonIncrease : ImageButton = view.findViewById(R.id.item_incr)
        private val cardView : LinearLayout = view.findViewById(R.id.items)
        private val radioButton : RadioButton = view.findViewById(R.id.radio)


        fun bind(counters: Counters, position: Int){
            textCounter.text = counters.title
            textNumber.text = counters.count.toString()
            buttonDecrease.setOnClickListener {
                if (CounterCheckInternet.isNetworkAvailable(itemView.context)){
                    updateValueDecre(counters, position)
                }else{
                    AlertConection.alertDecrease(itemView.context)
                }

            }

            buttonIncrease.setOnClickListener {
                if (CounterCheckInternet.isNetworkAvailable(itemView.context)){
                    updateValueIncre(counters, position)
                }else{
                    AlertConection.alertDecrease(itemView.context)
                }
            }

            cardView.setOnClickListener {
                selectedItemPosition = position
                 onClickItem?.invoke(position)
                 notifyDataSetChanged()
            }

            if(selectedItemPosition == position){
                if (radioButton.visibility == View.VISIBLE) {
                    cardView.setBackgroundColor(Color.parseColor("#F2F3F4"))
                    textNumber.visibility = View.VISIBLE
                    buttonDecrease.visibility = View.VISIBLE
                    buttonIncrease.visibility = View.VISIBLE
                    radioButton.visibility = View.GONE
                  } else {
                    cardView.setBackgroundColor(Color.parseColor("#ffdfbf"))
                    textNumber.visibility = View.GONE
                    buttonDecrease.visibility = View.GONE
                    buttonIncrease.visibility = View.GONE
                    radioButton.visibility = View.VISIBLE
                }
            }
            else {
                cardView.setBackgroundColor(Color.parseColor("#F2F3F4"))
                textNumber.visibility = View.VISIBLE
                buttonDecrease.visibility = View.VISIBLE
                buttonIncrease.visibility = View.VISIBLE
                radioButton.visibility = View.GONE
                radioButton.visibility = View.GONE
                }
        }
    }

    fun deleteItem(index: Int){
        val id = counterFisrtList[index].id
        counterFisrtList.removeAt(index)
        viewModel.delete(id)
        notifyDataSetChanged()
    }

    fun getItem(index: Int) = counterFisrtList[index].title

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                counterFisrtList = if (charSearch.isEmpty()) {
                    list
                } else {
                    val resultList  = mutableListOf<Counters>()
                    for (row in list) {
                        if (row.title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = counterFisrtList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                counterFisrtList = results?.values as MutableList<Counters>
                notifyDataSetChanged()
            }

        }
    }

    private fun updateValueDecre(counters: Counters, position: Int){
        if(counterFisrtList[position].count > 0){
            counterFisrtList[position].count = counters.count - 1
            viewModel.update(counterFisrtList[position])
            notifyDataSetChanged()
        }
    }
    private fun updateValueIncre(counters: Counters, position: Int){
        counterFisrtList[position].count = counters.count + 1
        viewModel.update(counterFisrtList[position])
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CounterViewHolder(v)
    }

    override fun getItemCount() = counterFisrtList.size

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        val listItem = counterFisrtList[position]
        holder.bind(listItem, position)

    }
}