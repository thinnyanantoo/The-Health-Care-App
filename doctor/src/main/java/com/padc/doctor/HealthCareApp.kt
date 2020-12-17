package com.padc.doctor

import android.app.Application
import android.content.Context
import androidx.work.*
import com.padc.doctor.utils.SessionManager
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.persistence.db.CareDB
import com.padc.shared.workers.GetCareWorker
import java.util.concurrent.TimeUnit

class HealthCareApp : Application() {

    override fun onCreate() {
        super.onCreate()
        HealthCareModelImpl.initDatabase(applicationContext)
        SessionManager.init(applicationContext)

        getCareOneTime()
        getCarePeriodically()
        getCareWhileInDozeMode()

         lateinit var mTheDB:CareDB
        fun initDatabase(context: Context) {
            mTheDB = CareDB.getDBInstance(context)
        }
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