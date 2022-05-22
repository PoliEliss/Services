package com.rorono.services

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() { //Service создается
        super.onCreate()
        log("onCreate")
    }



    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
        log("onDestroy")
    }



    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d("TEST", "onStartJob")
        coroutineScope.launch {
            for (i in 0 until 100) {
                delay(1000)
                log("Timer $i")
            }
            jobFinished(p0,true)
        }
       return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
       log("onStopJob")
        return true
    }

    private fun log(message: String) {
        Log.d("TEST", "Myservice:${message}")
    }


}