package com.preet.covid19tracker_20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.leo.simplearcloader.SimpleArcLoader
import kotlinx.android.synthetic.main.fragment_district_wise.*
import org.json.JSONException


class DistrictWiseFragment : Fragment() {

    private val requestQueue  by lazy {
        Volley.newRequestQueue(context)
    }


    private var stateName: String? = null

    private val simpleArcLoader: SimpleArcLoader
        get() = progressBar



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_district_wise, container, false)
    }

    override fun onResume() {
        super.onResume()

      //  val bundle:Bundle?=null

      //  no_cases_animation!!.visibility = View.INVISIBLE

        txt_no_cases!!.visibility = View.INVISIBLE

        districtRecycler!!.layoutManager = LinearLayoutManager(context)

//        stateName = activity?.intent?.getStringExtra("stateName")
//        txt_state_name!!.text = stateName

        parseJson()


    }

    private fun parseJson() {

        simpleArcLoader.start()

        val url = "https://api.covid19india.org/state_district_wise.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null
            , Response.Listener { response ->
                try {
                   // val state = response.getJSONObject(stateName)
                    val districtData = response.getJSONObject("statecode").getJSONObject("districtData")
                    val keys = districtData.names()
                    val length = keys.length()
                    val district = arrayOfNulls<String>(length)
                    val cases = arrayOfNulls<String>(length)
                    for (i in 0 until length) {
                        district[i] = keys.getString(i)
                        cases[i] = districtData.getJSONObject(district[i]).getString("confirmed")
                    }

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE


                    districtRecycler!!.adapter = DistrictAdapter(district, cases)
                } catch (e: JSONException) {
                    e.printStackTrace()
                   // no_cases_animation!!.visibility = View.VISIBLE
                    txt_no_cases!!.visibility = View.VISIBLE

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                }
            }, Response.ErrorListener { error -> error.printStackTrace()
                simpleArcLoader.stop()
                simpleArcLoader.visibility = View.GONE
            })
        requestQueue!!.add(request)
    }


}

