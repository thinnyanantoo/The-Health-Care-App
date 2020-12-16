package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.android.material.button.MaterialButton
import com.padc.shared.data.vos.PatientVO

@Dao
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
interface PatientDao {
    @Query("SELECT * FROM patient")
    @ColumnInfo
    fun getPatient(): LiveData<PatientVO>

    @Query("SELECT * FROM patient WHERE id = :id")
    @ColumnInfo
    fun getPatientById(id : String) : LiveData<PatientVO>



    @Delete
    @ColumnInfo
    fun deletePatient(Patient : List<PatientVO>)

    @Delete
    @ColumnInfo
    fun deletePATIENT(Patient: PatientVO)

    @Query("DELETE FROM patient")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertPatient(patient: List<PatientVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertDataToPatient(patient: PatientVO)
}

