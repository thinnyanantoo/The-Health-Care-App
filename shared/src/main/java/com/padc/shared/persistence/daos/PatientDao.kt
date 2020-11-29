package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.PatientVO
import retrofit2.http.DELETE

@Dao
interface PatientDao {
    @Query("SELECT * FROM patient")
    fun getPatient(): LiveData<PatientVO>

    @Query("SELECT * FROM patient WHERE id = :patientID")
    fun getPatientById(patientID : String) : LiveData<PatientVO>

    @DELETE
    fun deletePatient(Patient : PatientVO)

    @Query("DELETE FROM patient")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: List<PatientVO>)
}

