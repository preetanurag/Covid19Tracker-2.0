package com.preet.covid19tracker_20

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.ArrayList

class MyCustomAdapter(context: Context, private val countryModelsList: List<CountryModel>) : ArrayAdapter<CountryModel?>(context, R.layout.list_custom_item, countryModelsList) {
    private var countryModelsListFiltered: List<CountryModel>
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_custom_item, null, true)
        val tvCountryName = view.findViewById<TextView>(R.id.each_country)
        val imageView = view.findViewById<ImageView>(R.id.imageFlag)
        val totalcases:TextView=view.findViewById<TextView>(R.id.affected)
        val deaths:TextView=view.findViewById<TextView>(R.id.deaths)
        val recovered1:TextView=view.findViewById<TextView>(R.id.recovered)
        val critical:TextView=view.findViewById<TextView>(R.id.serious)
        val active:TextView=view.findViewById<TextView>(R.id.active)

        val pieChart:PieChart=view.findViewById<PieChart>(R.id.piechart)
        tvCountryName.text = countryModelsListFiltered[position].country
        val recovered=countryModelsListFiltered[position].recovered
        val totaldeaths=countryModelsListFiltered[position].deaths
        val tvActive=countryModelsListFiltered[position].active
      //  totalcases.text=  "Total Cases-"+countryModelsListFiltered[position].cases



        totalcases.text=countryModelsListFiltered[position].cases
        deaths.text=countryModelsListFiltered[position].deaths
        recovered1.text=countryModelsListFiltered[position].recovered
        critical.text=countryModelsListFiltered[position].critical
        active.text=countryModelsListFiltered[position].active



        pieChart.addPieSlice(PieModel("Cases", totalcases.text.toString().toInt().toFloat(), Color.parseColor("#FFA726")))
        pieChart.addPieSlice(PieModel("Recoverd", recovered.toString().toInt().toFloat(), Color.parseColor("#66BB6A")))
        pieChart.addPieSlice(PieModel("Deaths", totaldeaths.toString().toInt().toFloat(), Color.parseColor("#EF5350")))
        pieChart.addPieSlice(PieModel("Active", tvActive.toString().toInt().toFloat(), Color.parseColor("#29B6F6")))
        pieChart.startAnimation()

        Glide.with(context).load(countryModelsListFiltered[position].flag).into(imageView)
        return view
  }

    override fun getCount(): Int {
        return countryModelsListFiltered.size
    }

    override fun getItem(position: Int): CountryModel? {
        return countryModelsListFiltered[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint.length == 0) {
                    filterResults.count = countryModelsList.size
                    filterResults.values = countryModelsList
                } else {
                    val resultsModel: MutableList<CountryModel> = ArrayList()
                    val searchStr = constraint.toString().toLowerCase()
                    for (itemsModel in countryModelsList) {
                        if (itemsModel.country!!.toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel)
                        }
                        filterResults.count = resultsModel.size
                        filterResults.values = resultsModel
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                countryModelsListFiltered = results.values as List<CountryModel>
                AffectedCountriesFragment.countryModelsList = results.values as MutableList<CountryModel>
                notifyDataSetChanged()
            }
        }
    }

    init {
        countryModelsListFiltered = countryModelsList
    }
}