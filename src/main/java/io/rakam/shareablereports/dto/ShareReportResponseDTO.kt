package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ShareReportResponseDTO (
        @JsonProperty("resourceURL")
        private var resourceURL: String,
        @JsonProperty("access_token")
        private var accessToken:  String) {

    fun getResourceURL(): String {
        return this.resourceURL
    }

    fun getAccessToken(): String {
        return this.accessToken
    }

}