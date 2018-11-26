package io.rakam.shareablereports.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ShareReportResponseDTO {

    @JsonProperty("resourceURL")
    private var resourceURL: String? = null

    @JsonProperty("access_token")
    private var accessToken:  String? = null

    constructor(resourceURL: String?, accessToken: String?) {
        this.resourceURL = resourceURL
        this.accessToken = accessToken
    }

    fun getResourceURL(): String? {
        return this.resourceURL
    }

    fun getAccessToken(): String? {
        return this.accessToken
    }

}