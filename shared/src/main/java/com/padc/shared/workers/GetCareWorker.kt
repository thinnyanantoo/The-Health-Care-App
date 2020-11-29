package com.example.padc_thepodcast_tutorial_tyno.workers

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters

class GetCareWorker(context: Context, workerParams: WorkerParameters) :
    BaseWorkers(context, workerParams) {
    override fun doWork(): Result {
        var result = Result.failure()
        var id: String = ""

        mHealthCareModel.getDoctorFromFirebaseApiAndSaveToDatabase(
            onSuccess = {
                result = Result.success()
            },
            onError = {
                result = Result.failure()
            }
        )
        mHealthCareModel.getPatientFromFirebaseApiAndSaveToDatabase(
            onSuccess = {
                Log.e("KEy","Reach to response")
                result = Result.success()
            },
            onError = {
                Log.e("KEy","Did not reach to response")
                result = Result.failure()
            }
        )
        mHealthCareModel.getSpecialitiesFromFirebaseApiAndSaveToDatabase(
            onSuccess = {
                Log.e("KEy","Reach to response")
                result = Result.success()
            },
            onError = {
                Log.e("KEy","Did not reach to response")
                result = Result.failure()
            }
        )
        return result

    }
}
