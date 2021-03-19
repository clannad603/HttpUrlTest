package com.example.httpurltest

/***
 * created by hashcode
 */
interface OnResultResponse {
    fun onRightReturn(string: String)

    fun onErrorReturn(e:Exception)
}