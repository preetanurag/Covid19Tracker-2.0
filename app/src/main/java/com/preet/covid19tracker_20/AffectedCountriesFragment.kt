package com.preet.covid19tracker_20

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_affected_countries.*
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList


class AffectedCountriesFragment : Fragment() {

//    var edtSearch: EditText? = null
//    var listView: ListView? = null
//    var simpleArcLoader: SimpleArcLoader? = null
  var countryModel: CountryModel? = null
  var myCustomAdapter: MyCustomAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_affected_countries, container, false)
    }

    override fun onResume() {
        super.onResume()

        search_back.setOnClickListener {
            findNavController().navigateUp()
        }

//        edtSearch = findViewById(R.id.edtSearch)
//        listView = findViewById(R.id.listView)
//        simpleArcLoader = findViewById(R.id.loader)

        fetchData()
//        listView!!.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
////            startActivity(
////                Intent(activity, DetailActivityFragment::class.java).putExtra(
////                    "position",
////                    position
////                )
////            )
//            val bundle = bundleOf("position" to position)
//
//            navController.navigate(R.id.action_affectedCountriesFragment_to_detailActivityFragment,bundle)
//        })
        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                myCustomAdapter!!.filter.filter(s)
                myCustomAdapter!!.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable) {}
        })




    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home)
//            activity?.finish()
//        return super.onOptionsItemSelected(item)
//    }

    private fun fetchData() {
        val url = "https://corona.lmao.ninja/v2/countries/"
        loader.start()
        val request = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val countryName = jsonObject.getString("country")
                        val cases = jsonObject.getString("cases")
                        val todayCases = jsonObject.getString("todayCases")
                        val deaths = jsonObject.getString("deaths")
                        val todayDeaths = jsonObject.getString("todayDeaths")
                        val recovered = jsonObject.getString("recovered")
                        val active = jsonObject.getString("active")
                        val critical = jsonObject.getString("critical")
                        val `object` = jsonObject.getJSONObject("countryInfo")
                        val flagUrl = `object`.getString("flag")
                        countryModel = CountryModel(flagUrl, countryName, cases, todayCases, deaths, todayDeaths, recovered, active, critical)
                        countryModelsList.add(countryModel!!)
                    }
                    myCustomAdapter = context?.let { MyCustomAdapter(it, countryModelsList) }
                    listView!!.adapter = myCustomAdapter
                   loader.stop()
                   loader.visibility = View.GONE



                } catch (e: JSONException) {
                    e.printStackTrace()
                   loader.stop()
                   loader.visibility = View.GONE
                }
            }, Response.ErrorListener { error ->
               loader.stop()
               loader.visibility = View.GONE
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)
    }

    companion object {
        var countryModelsList: MutableList<CountryModel> = ArrayList()
    }
}
