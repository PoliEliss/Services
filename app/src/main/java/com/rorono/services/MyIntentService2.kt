package com.rorono.services

import android.app.IntentService
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.rorono.services.Constants.NAME
import kotlinx.coroutines.*

class MyIntentService2 : IntentService(NAME) {


    override fun onCreate() { //Service создается
        super.onCreate()
        setIntentRedelivery(true)

    }

    override fun onHandleIntent(p0: Intent?) {

        val page = p0?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 10) {
            Thread.sleep(1000)
            Log.d("TEST", "$i $page")

        }
    }

    override fun onDestroy() {

        super.onDestroy()

    }


    companion object {
        private const val NAME = "MyIntentService"
        private const val PAGE = "page"
        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentService2::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}