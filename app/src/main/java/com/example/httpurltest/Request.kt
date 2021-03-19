package com.example.httpurltest

import android.util.Log
import kotlinx.coroutines.*
import java.io.InputStream
import java.io.OutputStream
import java.lang.Runnable
import java.net.HttpURLConnection
import java.net.URL
import java.sql.Connection

/***
 * created by hashcode
 */
object Request {

    /***
     * get方法
     *
     */
    fun getRequest(url: String, onResultResponse: OnResultResponse){
        val runnable= Runnable{
            val connection=ConnectUtil(url)
            try {
//                connection.apply {
//                    requestMethod="GET"
//                    connectTimeout=8000
//                    readTimeout=8000
//                    doInput=true
//                }
    connection.setMethod("GET")
            .doInput(true)
            .setTime(8000)
            .connectTime(8000)
                onResultResponse.onRightReturn(steamToString(connection.httpConnect))
          }catch (e: Exception){
                e.printStackTrace()
                onResultResponse.onErrorReturn(e)
          }finally {
                connection.disConnect()
          }
            }
        ThreadPoolUtil.getInstance().addTask(runnable)
    }

    /***
     * post方法
     */
    fun sendPost(url: String,params:HashMap<String,String>, onResultResponse: OnResultResponse){
    val runnable= Runnable {
        val connection=ConnectUtil(url)
        try {
            connection.setMethod("POST")
                    .connectTime(8000)
                    .setTime(8000)
                    .doInput(true)
                    .doOutput(true)
            val dataToWrite=StringBuilder()
            for (key in params.keys){
                dataToWrite.append(key).append("=").append(params[key]).append("&")
            }
            connection.setConnect()
            val outputStream:OutputStream=connection.getOutputSteam()
            outputStream.write(dataToWrite.substring(0, dataToWrite.length- 1).toByteArray())
            onResultResponse.onRightReturn(steamToString(connection.httpConnect))

        }catch (e:Exception){
            e.printStackTrace()
            onResultResponse.onErrorReturn(e)
        }finally {
            connection.disConnect()
        }
    }
    ThreadPoolUtil.getInstance().addTask(runnable)
}

    /***
     * 转化
     */
   private fun steamToString(connection:HttpURLConnection):String{
       val stringBuilder= StringBuilder()
       val inputStream: InputStream=connection.inputStream
       inputStream.bufferedReader().forEachLine {
           stringBuilder.append(it)
           Log.d("thread", "getResult: $it")
       }
       inputStream.close()
       return stringBuilder.toString()
   }
}