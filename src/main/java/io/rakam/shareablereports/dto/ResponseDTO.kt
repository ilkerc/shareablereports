package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

open class ResponseDTO {

    @JsonProperty("timeStamp")
    private var timeStamp: Long = 0
    @JsonProperty("status")
    private var status: Long = 0

    constructor(status: Long){
        this.timeStamp = System.currentTimeMillis()
        this.status = status
    }

    fun getStatus() : Long{
        return this.status
    }
}