package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

class SuccessResponseDTO<T>: ResponseDTO {

    @JsonProperty("response")
    private val response: T

    constructor(status: Long, response: T): super(status = status) {
        this.response = response
    }

    fun getResponse(): T{
        return response;
    }
}