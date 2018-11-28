package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

class FailureResponseDTO(status: Long, @JsonProperty("message")
private val message: String) : ResponseDTO(status = status) {

    fun getMessage(): String {
        return this.message
    }
}