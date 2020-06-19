package com.preet.covid19tracker_20

import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.fragment_helpline.*
import org.json.JSONException


class HelplineFragment : Fragment() {

    private val recyclerView: RecyclerView
        get() = recycler_help_no

    private val requestQueue  by lazy {
        Volley.newRequestQueue(context)
    }

  private val simpleArcLoader: SimpleArcLoader
       get() = progressBar


    private  var helpData: Array<HelplineModel> = arrayOf()





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_helpline, container, false)
    }

    override fun onResume() {
        super.onResume()

        recyclerView.layoutManager = LinearLayoutManager(context)
        parseJson()


    }

    private fun parseJson() {

       simpleArcLoader.start()
        val url = "https://api.rootnet.in/covid19-in/contacts"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val array = response.getJSONObject("data").getJSONObject("contacts").getJSONArray("regional")
                    val gsonBuilder = GsonBuilder()
                    val gson: Gson = gsonBuilder.create()
                    helpData = gson.fromJson(
                        array.toString(),
                        Array<HelplineModel>::class.java
                    )

                    simpleArcLoader.stop()
                    simpleArcLoader.visibility = View.GONE
                    recyclerView.adapter = HelplineAdapter(helpData).apply {
                        onClickListener={number->
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
                            startActivity(intent)
                        }
                    }

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

