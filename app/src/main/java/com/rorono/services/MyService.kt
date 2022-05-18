package com.rorono.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() { //Service создается
        super.onCreate()
        log("onCreate")
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int { // выполняется все работа сервиса
        log("onStartCommand")
        coroutineScope.launch {
            for (i in 0 until 10) {
                delay(1000)
                log("Timer $i")
            }
        }

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
        log("onDestroy")
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(message: String) {
        Log.d("TEST", "Myservice:${message}")
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MyService::class.java)
        }
    }
}