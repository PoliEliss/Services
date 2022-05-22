package com.rorono.services

import android.app.IntentService
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.rorono.services.Constants.NAME
import kotlinx.coroutines.*

class MyIntentService : IntentService(NAME) {



    override fun onCreate() { //Service создается
        super.onCreate()
        setIntentRedelivery(true)

    }

    override fun onHandleIntent(p0: Intent?) {
        for (i in 0 until 10) {
            Thread.sleep(1000)

        }
    }
    override fun onDestroy() {

        super.onDestroy()

    }



    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}