package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

class SuccessResponseDTO<T>(status: Long, @JsonProperty("response")
private val response: T) : ResponseDTO(status = status) {

    fun getResponse(): T{
        return response;
    }
}