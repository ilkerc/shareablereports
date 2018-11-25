package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.omg.CORBA.Object

class FailureResponseDTO: ResponseDTO {

    @JsonProperty("message")
    private val message: String

    constructor(status: Long, message: String) : super(status) {
        this.message = message
    }
}