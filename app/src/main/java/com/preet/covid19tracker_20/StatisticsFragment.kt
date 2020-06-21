package com.preet.covid19tracker_20

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.eazegraph.lib.models.PieModel
import org.json.JSONException
import org.json.JSONObject


class StatisticsFragment : Fragment() {


    private val requestQueue  by lazy {
        Volley.newRequestQueue(context)
    }

    private var response1: JSONObject? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()

        moredeatils.setOnClickListener {
            navController.navigate(R.id.action_statisticsFragment_to_moreDetailsFragment)
        }
        global.setOnClickListener {

            navController.navigate(R.id.action_statisticsFragment_to_globalFragment)

        }



        stat_backbtn.setOnClickListener {
           navController.navigate(R.id.action_statisticsFragment_to_dashBoardFragment)
        }
        parseJson()
    }


    private fun parseJson() {
        val url = "https://api.covid19india.org/data.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                response1 = response
                try {
                    val array = response.getJSONArray("statewise")
                    val `object` = array.getJSONObject(0)
                    Log.d("dataaa", `object`.toString())
                    active.text = `object`.getString("active")
                    affected.text = `object`.getString("confirmed")
                    recovered.text = `object`.getString("recovered")
                    deaths.text = `object`.getString("deaths")

                    piechart.addPieSlice(PieModel("Cases", affected.text.toString().toInt().toFloat(), Color.parseColor("#ffb259")))
                    piechart.addPieSlice(PieModel("Recoverd", recovered.text.toString().toInt().toFloat(), Color.parseColor("#43d97b")))
                    piechart.addPieSlice(PieModel("Deaths", deaths.text.toString().toInt().toFloat(), Color.parseColor("#ff5959")))
                    piechart.addPieSlice(PieModel("Active", active.text.toString().toInt().toFloat(), Color.parseColor("#4db5ff")))
                    piechart.startAnimation()


//                    val lastUpdated = "Last Updated: " + `object`.getString("lastupdatedtime")
//                    txt_updated!!.text = lastUpdated
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue!!.add(request)
    }



}

