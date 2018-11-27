package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

class FailureResponseDTO: ResponseDTO {

    @JsonProperty("message")
    private val message: String

    constructor(status: Long, message: String): super(status = status) {
        this.message = message
    }

    fun getMessage(): String {
        return this.message
    }
}