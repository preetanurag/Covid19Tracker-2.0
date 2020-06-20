package com.preet.covid19tracker_20

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StateAdapter (private val stateData: Array<StateModel>) : RecyclerView.Adapter<StateAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateAdapter.myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.statelist, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stateData.size - 1
    }

    override fun onBindViewHolder(holder: StateAdapter.myViewHolder, i: Int) {

        var i = i
        i++
        val stateName = stateData[i].getStateName()
        val confirmed = stateData[i].getConfirmed()
        val active = stateData[i].getActive()
        val recovered = stateData[i].getRecovered()
        val deaths = stateData[i].getDeaths()
      //  val lastupdatedtime = stateData[i].getLastupdatedtime()
        holder.txt_confirmed_st.text = confirmed
        holder.txt_active_st.text = active
        holder.txt_recovered_st.text = recovered
        holder.txt_deaths_st.text = deaths
        holder.txt_state.text = stateName
       // holder.txt_updated_stt.text = lastupdatedtime
    }



        class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var txt_confirmed_st: TextView
            var txt_active_st: TextView
            var txt_recovered_st: TextView
            var txt_deaths_st: TextView
            var txt_state: TextView
            //var txt_updated_stt: TextView

            init {
                txt_confirmed_st = itemView.findViewById(R.id.affected)
                txt_active_st = itemView.findViewById(R.id.active)
                txt_recovered_st = itemView.findViewById(R.id.recovered)
                txt_deaths_st = itemView.findViewById(R.id.deaths)
                txt_state = itemView.findViewById(R.id.state_name)
                // txt_updated_stt = itemView.findViewById(R.id.txt_updated_stt)
            }
        }
}


