package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

open class ResponseDTO (
        @JsonProperty("status")
        private var status: Long){

    @JsonProperty("timeStamp")
    private val timeStamp: Long = System.currentTimeMillis()

    fun getStatus(): Long{
        return this.status
    }
}