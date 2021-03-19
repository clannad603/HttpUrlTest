package com.example.httpurltest

import android.util.Log
import android.util.Log.d
import java.util.concurrent.*
import java.util.logging.Logger

/***
 * created by hashcode
 */
class ThreadPoolUtil {
    companion object {
        fun getInstance() = SingleHolder.SINGLE_HOLDER
    }

    object SingleHolder {
        val SINGLE_HOLDER = ThreadPoolUtil()
    }
    /***
     * 获取cpu核心数
     */
    private val CPU_COUNT = Runtime.getRuntime().availableProcessors()

    /***
     * 线程池核心数
     */
    private val CORE_POOL_SIZE = CPU_COUNT + 1

    /***
     * 线程池最大核心数
     */
    private val MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1
    /**
     * 线程活跃时间 秒，超时线程会被回收
     * */
    private val KEEP_ALIVE_TIME: Long = 3
    /**
     * 等待队列大小
     * */
    private val QUEUE_SIZE = 128
    private fun getExecutor():ThreadPoolExecutor{
       val threadPoolExecutor = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                ArrayBlockingQueue<Runnable>(QUEUE_SIZE),
                Executors.defaultThreadFactory(),
                RejectedExecutionHandler { _, _ ->

                }
        )
        //允许核心线程闲置超时时被回收
        threadPoolExecutor.allowCoreThreadTimeOut(true)
        return threadPoolExecutor
    }
    fun removeTask( runnable: Runnable) {
        getExecutor().queue?.remove(runnable)
    }
    fun addTask(runnable: Runnable){
        getExecutor().execute(runnable)
    }
    fun exitThreadPool() {
       getExecutor().shutdownNow()
    }



}