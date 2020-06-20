package com.preet.covid19tracker_20

class RiskModel {

    var district: String? = null
    private var zone: String? = null
    var state: String? = null

    fun RiskModel(
        district: String?,
        zone: String?,
        state: String?
    ) {
        this.district = district
        this.zone = zone
        this.state = state
    }

    fun getDistrictName(): String? {
        return district
    }

    fun getZone(): String? {
        return zone
    }

    fun getStateName(): String? {
        return state
    }

}