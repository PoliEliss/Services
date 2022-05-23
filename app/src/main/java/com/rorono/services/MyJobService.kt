package com.rorono.services

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi
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


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStartJob(p0: JobParameters?): Boolean {
        coroutineScope.launch {
            var workItem = p0?.dequeueWork()
            while (workItem != null) {
                val page = workItem.intent.getIntExtra(PAGE, 0)
                for (i in 0 until 5) {
                    delay(1000)
                    log("Timer $i $page")
                }
                p0?.completeWork(workItem)
                workItem = p0?.dequeueWork()
            }
            jobFinished(p0, false)
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

    companion object {
        const val JOB_ID = 61
        private const val PAGE = "page"

        fun newIntent(page: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, page)
            }
        }
    }
}