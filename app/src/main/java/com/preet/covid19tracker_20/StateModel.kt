package com.preet.covid19tracker_20

class StateModel {


   private var state: String? = null
    private var confirmed: String? = null
   private var active: String? = null
   private var recovered: String? = null
  private  var deaths: String? = null
   private var lastupdatedtime: String? = null

    fun StateModel(
        state: String?,
        confirmed: String?,
        active: String?,
        recovered: String?,
        deaths: String?,
        lastupdatedtime: String?
    ) {
        this.state = state
        this.confirmed = confirmed
        this.active = active
        this.recovered = recovered
        this.deaths = deaths
        this.lastupdatedtime = lastupdatedtime
    }

    fun getStateName(): String? {
        return state
    }

    fun getConfirmed(): String? {
        return confirmed
    }

    fun getActive(): String? {
        return active
    }

    fun getRecovered(): String? {
        return recovered
    }

    fun getDeaths(): String? {
        return deaths
    }

    fun getLastupdatedtime(): String? {
        return lastupdatedtime
    }


}