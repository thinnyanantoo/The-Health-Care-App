package com.padc.shared

import android.app.Application
import androidx.work.*
import com.example.padc_thepodcast_tutorial_tyno.workers.GetCareWorker
import com.padc.shared.data.models.impls.HealthCareModelImpl
import java.util.concurrent.TimeUnit

class HealthCareApp : Application() {

    override fun onCreate() {
        super.onCreate()
        HealthCareModelImpl.initDatabase(applicationContext)

        getCareOneTime()
        getCarePeriodically()
        getCareWhileInDozeMode()
    }

    private fun getCareOneTime() {
        val getEventsWorkRequest = OneTimeWorkRequest.Builder(GetCareWorker::class.java)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsWorkRequest)
    }

    private fun getCarePeriodically() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getEventsPeroidicallyWorkRequest = PeriodicWorkRequest
            .Builder(GetCareWorker::class.java, 30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsPeroidicallyWorkRequest)
    }

    private fun getCareWhileInDozeMode() {
        val constraints = Constraints
            .Builder()
            .setRequiresDeviceIdle(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getEventsWhileInDozeModeWorkRequest = PeriodicWorkRequest
            .Builder(GetCareWorker::class.java, 1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(getEventsWhileInDozeModeWorkRequest)
    }

}