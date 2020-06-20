package com.preet.covid19tracker_20

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.eazegraph.lib.models.PieModel
import org.json.JSONException
import org.json.JSONObject


class GlobalFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
        }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()

        global.setBackgroundResource(R.drawable.spinner)
        india.setBackgroundResource(R.drawable.send_sms_button)
        moredeatils.setText("Track all Countries")

       india.setOnClickListener {
           navController.navigate(R.id.action_globalFragment_to_statisticsFragment)
       }
        fetchData()
    }
    private fun fetchData() {
        val url = "https://corona.lmao.ninja/v2/all/"
      //  simpleArcLoader.start()
        val request = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    affected.text = jsonObject.getString("cases")
                    recovered.text = jsonObject.getString("recovered")
                  //  tvCritical!!.text = jsonObject.getString("critical")
                    active.text = jsonObject.getString("active")
                   // tvTodayCases!!.text = jsonObject.getString("todayCases")
                    deaths.text = jsonObject.getString("deaths")
                  //  tvTodayDeaths!!.text = jsonObject.getString("todayDeaths")
                 //  tvAffectedCountries!!.text = jsonObject.getString("affectedCountries")

                    piaChart()


                } catch (e: JSONException) {
                    e.printStackTrace()
                    //stoploader()
                }
            }, Response.ErrorListener { error ->

               // stoploader()

                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(request)
    }

//    fun goTrackCountries(view: View?) {
//        startActivity(Intent(applicationContext, AffectedCountries::class.java))
//    }


    private fun piaChart(){
        piechart.addPieSlice(PieModel("Cases", affected.text.toString().toInt().toFloat(), Color.parseColor("#ffb259")))
        piechart.addPieSlice(PieModel("Recoverd", recovered.text.toString().toInt().toFloat(), Color.parseColor("#43d97b")))
        piechart.addPieSlice(PieModel("Deaths", deaths.text.toString().toInt().toFloat(), Color.parseColor("#ff5959")))
        piechart.addPieSlice(PieModel("Active", active.text.toString().toInt().toFloat(), Color.parseColor("#4db5ff")))
        piechart.startAnimation()
    }

//    private fun stoploader(){
//        simpleArcLoader.stop()
//        simpleArcLoader.visibility = View.GONE
//        scrollView.visibility = View.VISIBLE
//    }


}







