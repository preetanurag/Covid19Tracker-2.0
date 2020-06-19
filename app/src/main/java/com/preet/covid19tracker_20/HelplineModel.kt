package com.preet.covid19tracker_20

class HelplineModel {


    private var number: String? = null
    private var loc: String? = null

    fun HelplineModel(number: String?, loc: String?) {
        this.number = number
        this.loc = loc
    }

    fun getNumber(): String? {
        return number
    }

    fun getLoc(): String? {
        return loc
    }

}