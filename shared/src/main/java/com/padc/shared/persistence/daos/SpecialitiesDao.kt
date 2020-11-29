package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.SpecialityVO
import retrofit2.http.DELETE

@Dao
interface SpecialitiesDao {
    @Query("SELECT * FROM specialitytable")
    fun getSpecialityList(): LiveData<List<SpecialityVO>>

    @Query("SELECT * FROM specialitytable WHERE id = :specialityID")
    fun getSpecialityById(specialityID : String) : LiveData<SpecialityVO>

    @DELETE
    fun deleteSpeciality(Patient :SpecialityVO)

    @Query("DELETE FROM specialitytable")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeicality(speicality: List<SpecialityVO>)
}