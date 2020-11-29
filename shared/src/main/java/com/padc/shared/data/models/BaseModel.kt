package com.padc.shared.data.models

import android.content.Context
import com.padc.shared.network.FirebaseApi
import com.padc.shared.persistence.db.CareDB

abstract class BaseModel {
    open lateinit var mFirebaseApi : FirebaseApi
    protected lateinit var mTheDB:CareDB


    fun initDatabase(context: Context) {
        mTheDB = CareDB.getDBInstance(context)
    }
}