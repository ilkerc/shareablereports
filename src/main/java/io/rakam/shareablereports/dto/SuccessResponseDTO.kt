package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.omg.CORBA.Object

class SuccessResponseDTO<T>: ResponseDTO {

    @JsonProperty("response")
    private val response: T

    constructor(status: Long, response: T) : super(status) {
        this.response = response
    }
}