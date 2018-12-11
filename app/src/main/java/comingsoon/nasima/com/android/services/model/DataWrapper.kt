package com.cognitiveclouds.ds.services.model

class DataWrapper<T>(responseData: T?, errorResponse: ErrorResponse?) {
    var data: T? = responseData
    var error: ErrorResponse? = errorResponse
}