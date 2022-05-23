package com.rorono.services

import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.rorono.services.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // private var id = 0

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(MyService.newIntent(this))
        }
        binding.foregroundService.setOnClickListener {
            MyForegroundService.startService(this, "ForegroundService is Running")
        }
        binding.intentService.setOnClickListener {
            ContextCompat.startForegroundService(this, MyIntentService.newIntent(this))
        }
        binding.jobScheduler.setOnClickListener {
            val componentName = ComponentName(this, MyJobService::class.java)
            val jobInfo = JobInfo.Builder(MyJobService.JOB_ID, componentName)

                .build()
            // .setRequiresCharging(true)
            //  .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val intent = MyJobService.newIntent(page++)
            jobScheduler.enqueue(jobInfo, JobWorkItem(intent))
        }
    }

    /*private fun showNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)

      /*  val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Title")
            .setContentText("jjjjjj")
            .build()*/



        notificationManager.notify(id++, notification)
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME ="channel_name"
    }*/
}