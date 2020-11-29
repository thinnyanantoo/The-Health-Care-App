package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.DoctorVO
import retrofit2.http.DELETE

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctor")
    fun getDoctorsLit(): LiveData<DoctorVO>

    @Query("SELECT * FROM doctor WHERE id = :doctorID")
    fun getDoctorById(doctorID : String) : LiveData<DoctorVO>

    @DELETE
    fun deleteDoctor(doctor : DoctorVO)

    @Query("DELETE FROM doctor")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetDoctor(doctor: List<DoctorVO>)
}