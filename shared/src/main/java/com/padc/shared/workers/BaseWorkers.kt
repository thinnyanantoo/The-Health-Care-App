package com.padc.shared.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl

abstract class BaseWorkers(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    val mHealthCareModel: HealthCareModel =
        HealthCareModelImpl
}