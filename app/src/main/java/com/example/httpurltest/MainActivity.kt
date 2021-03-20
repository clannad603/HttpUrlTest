package com.example.httpurltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val URL:String="https://www.wanandroid.com/project/tree/json"
    private val textLiveData=MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initObserve()
    }

    private fun initObserve() {
        textLiveData.observe(this, Observer {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initView(){
        val map= mapOf<String,String>("username" to "huang.","password" to "")
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
                  val jsonObjects=JSONObject(string)
                  val msg=jsonObjects.getString("errorMsg")
                     //  Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
                       /***
                        * 线程仍在子线程
                        */
//                      runOnUiThread {
//                          Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
//                      }
                       /***
                        * 此处可以在线程池工具类进行增加ui线程管理，此处做法有问题
                        * 尝试使用livedata改造一下
                        */
                       textLiveData.postValue(msg)
                       /***
                        * 这样就不用开启ui线程，而是使用livedata的性质传出去，可以通过toast成功显示证明，
                        * 但livedata底层还没有弄懂
                        */
                       /***
                        * livedata 的post原理，是在此方法类使用了线程池，将其值传入至主线程
                        * ArchTaskExecutor.getinstance().postToMainThread
                        * 所以本质上还是线程池操作
                        * 及理论上可以通过改写工具类实现
                        */
                   }

                   override fun onErrorReturn(e: Exception) {

                   }

               })

      }




    }
}