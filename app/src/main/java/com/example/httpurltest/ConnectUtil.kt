package com.example.httpurltest

import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

/***
 * created by hashcode
 */
/***
 * 链式调用的实验
 */
class ConnectUtil(val mUrl :String) {
    private val url = URL(mUrl)
    var httpConnect = url.openConnection() as HttpURLConnection
    fun setTime(timeout:Int): ConnectUtil {
        httpConnect.readTimeout=timeout
        return this
    }
    fun setMethod(method:String): ConnectUtil {
        httpConnect.requestMethod=method
        return this
    }
    fun setConnect(): ConnectUtil {
            httpConnect.connect()
        return this
    }
    fun doInput(boolean: Boolean): ConnectUtil {
        httpConnect.doInput=boolean
        return this
    }
    fun doOutput(boolean: Boolean):ConnectUtil{
        httpConnect.doOutput = boolean
        return this
    }
    fun connectTime(timeout: Int):ConnectUtil{
        httpConnect.connectTimeout = timeout
        return  this
    }
    fun defaultCache(boolean: Boolean):ConnectUtil{
        httpConnect.defaultUseCaches=boolean
        return  this
    }
    fun getOutput(): OutputStream? {
        return httpConnect.outputStream
    }
    fun changeToBuffer(outputStream: OutputStream): BufferedWriter {
        return BufferedWriter(OutputStreamWriter(outputStream))
    }
    fun disConnect(){
        httpConnect.disconnect()
    }
    fun getInputSteam(): InputStream {
        return httpConnect.inputStream
    }
    fun getOutputSteam():OutputStream{
        return httpConnect.outputStream
    }

}