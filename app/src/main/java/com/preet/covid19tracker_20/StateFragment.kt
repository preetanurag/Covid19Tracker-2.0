package com.preet.covid19tracker_20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leo.simplearcloader.SimpleArcLoader
import kotlinx.android.synthetic.main.fragment_state.*
import org.json.JSONException


class StateFragment : Fragment() {

    private val recyclerView: RecyclerView
        get() = recycler

    private val requestQueue  by lazy {
        Volley.newRequestQueue(context)
    }

    private val simpleArcLoader: SimpleArcLoader
        get() = progressBar

    private lateinit var stateData: Array<StateModel>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state, container, false)
    }

    override fun onResume() {
        super.onResume()

        recyclerView.layoutManager = LinearLayoutManager(context)

        parseJson()
    }

    private fun parseJson() {

        simpleArcLoader.start()

        val url = "https://api.covid19india.org/data.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val array = response.getJSONArray("statewise")
                    val gsonBuilder = GsonBuilder()
                    val gson: Gson = gsonBuilder.create()
                    stateData = gson.fromJson(
                        array.toString(),
                        Array<StateModel>::class.java
                    )

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE

                    recyclerView.adapter = StateAdapter(stateData)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE
                }
            },
            Response.ErrorListener {error ->
                simpleArcLoader.stop()
                simpleArcLoader.visibility = View.GONE

                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()

            }
        )
        requestQueue?.add(request)
    }

}
