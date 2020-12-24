package com.padc.shared.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.padc.shared.data.vos.*
import com.padc.shared.persistence.daos.*
import com.padc.shared.persistence.typeConverters.*


@Database(
    entities = [SpecialityVO::class, DoctorVO::class, PatientVO::class, CheckOutVO::class, MedicineVO::class, GeneralQuestionVO::class, ConsultationRequestVO::class,SpecialQuestionVO::class, CaseSummaryVO::class],
    version = 5, exportSchema = false
)
@TypeConverters(
    SpecialQuestionTypeConverter::class,
    GeneralQuestionTypeConverters::class,
    MedicineTypeConverter::class,
    RoutineTypeConverter::class,
    RecentlyDoctorTypeConverter::class,
    PatientTypeConverter::class,
    DoctorTypeConverter::class,
    ChatMessageTypeConverter::class,
    CaseSummaryTypeConverter::class,
    DeliveryRoutineTypeConverter::class,
    PrescriptionTypeConverter::class,
    SendByVOTypeConverter::class,
     AddressTypeConverters::class,
    OneTimeGeneralQuestionTypeConverter::class
)
abstract class CareDB : RoomDatabase() {
    companion object {
        val DB_NAME = "CareDB"
        var dbInstance: CareDB? = null
        fun getDBInstance(context: Context): CareDB {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(
                        context, CareDB::class.java, DB_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i = dbInstance
            return i!!
        }
    }

    abstract fun doctorDao(): DoctorDao
    abstract fun patientDao(): PatientDao
    abstract fun checkOutDao(): CheckOutDao
    abstract fun spelicityDao(): SpecialitiesDao
    abstract fun medicineDao(): MedicineDao
    abstract fun specialQuestionDao(): SpecialQuestionDao
    abstract fun generalDao(): GeneralQuestonDao
    abstract fun caseSummaryDao(): CaseSummaryDao
    abstract fun consultationRequestDao(): ConsultationRequestDao


}

