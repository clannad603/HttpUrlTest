package com.example.httpurltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val URL:String="https://www.wanandroid.com/project/tree/json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    private fun initView(){
        val map= mapOf<String,String>("username" to "huang.","password" to "clannadhr01")
      button.setOnClickListener {
//           Request.getRequest(URL,object :OnResultResponse{
//        override fun onRightReturn(string: String) {
//            textView.text=string
//        }
//
//        override fun onErrorReturn(e: Exception) {
//
//        }
//
//    })
       Request.sendPost("https://www.wanandroid.com/user/login",
               map as HashMap<String, String>,
               object :OnResultResponse{
                   override fun onRightReturn(string: String) {
                   textView.text=string
                   }

                   override fun onErrorReturn(e: Exception) {

                   }

               })
      }




    }
}