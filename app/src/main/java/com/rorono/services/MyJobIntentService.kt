package com.rorono.services

import android.app.IntentService
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import com.rorono.services.Constants.NAME
import kotlinx.coroutines.*

class MyJobIntentService : JobIntentService() {


    override fun onCreate() { //Service создается
        super.onCreate()
    }


    override fun onDestroy() {

        super.onDestroy()

    }

    override fun onHandleWork(intent: Intent) {
        val page = intent.getIntExtra(PAGE, 0)
        for (i in 0 until 10) {
            Thread.sleep(1000)
            Log.d("TEST", "$i $page")
        }
    }


    companion object {
        private const val PAGE = "page"
        private const val JOB_ID = 111

        fun enqueue(context: Context,page: Int){
           enqueueWork(context,MyJobIntentService::class.java, JOB_ID
            , newIntent(context,page))

        }
     private   fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}