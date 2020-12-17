package com.padc.shared.workers

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import com.padc.shared.data.vos.GeneralQuestionVO

class GetCareWorker(context: Context, workerParams: WorkerParameters) :
    BaseWorkers(context, workerParams) {
    override fun doWork(): Result {
        var result = Result.failure()
        var name: String = ""
        var generalQuestionVO = GeneralQuestionVO()

//        mHealthCareModel.getPatientFromFirebaseApiAndSaveToDatabase(
//            onSuccess = {
//                Log.e("KEy","Reach to response")
//                result = Result.success()
//            },
//            onError = {
//                Log.e("KEy","Did not reach to response")
//                result = Result.failure()
//            }
//        )
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

        mHealthCareModel.getGeneralQuestionFromFirebaseApiAndSaveToDataBase(
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
