package com.test.assignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class EnglandAndWales {
    @SerializedName("division")
    @Expose
     var division: String? = null
    @SerializedName("events")
    @Expose
     var events: ArrayList<Event>? = null

}