package com.priyank.countrypicker;

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import kotlinx.android.synthetic.main.item_country.view.*

class CountryPickerAdapter(
    private val list: List<Country>,
    private val onClickCountryName: OnClickCountryName
) :
    RecyclerView.Adapter<CountryPickerAdapter.MyViewHolder>(), Filterable {

    private var listFiltered = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rowView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return MyViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return listFiltered.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(listFiltered[position]) {
            //Do you Write
            holder.itemView.imgFlag.setImageResource(this.flag)
            holder.itemView.countryName.text = this.name
            holder.itemView.countryDialCode.text = this.dialCode

            holder.itemView.setOnClickListener {
                onClickCountryName.onClick(this)
            }
        }
    }

    class MyViewHolder(rowView: View) : RecyclerView.ViewHolder(rowView)

    interface OnClickCountryName {
        fun onClick(country: Country)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                listFiltered = if (charString.isEmpty()) {
                    list
                } else {
                    val filteredList = arrayListOf<Country>()
                    for (row in list) {
                        if (row.name.toLowerCase().contains(charString.toLowerCase()) || row.dialCode.toLowerCase().contains(
                                charString.toLowerCase()
                            )
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFiltered = results?.values as ArrayList<Country>
                notifyDataSetChanged()
            }
        }
    }
}