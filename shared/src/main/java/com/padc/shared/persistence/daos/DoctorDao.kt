package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.DoctorVO

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctor")
    @ColumnInfo
    fun getDoctorsLit(): LiveData<DoctorVO>

    @Query("SELECT * FROM doctor WHERE id = :doctorID")
    @ColumnInfo
    fun getDoctorById(doctorID : String) : LiveData<DoctorVO>

//    @Query("SELECT * FROM doctor WHERE specialityName = :doctorName")
//    @ColumnInfo
//    fun getDoctorBySpecialityName(doctorName : String) : LiveData<DoctorVO>

   @Delete
   @ColumnInfo
    fun deleteDoctor(doctor : DoctorVO)

    @Query("DELETE FROM doctor")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insetDoctor(doctor: List<DoctorVO>)
}