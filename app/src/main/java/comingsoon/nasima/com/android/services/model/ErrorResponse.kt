package com.cognitiveclouds.ds.services.model

class ErrorResponse(status: Boolean?, message: String, title: String?) {

    var status: Boolean? = null

    var message: String? = null

    var title: String? = null

    init {
        this.status = status
        this.message = message
        this.title = title
    }

}