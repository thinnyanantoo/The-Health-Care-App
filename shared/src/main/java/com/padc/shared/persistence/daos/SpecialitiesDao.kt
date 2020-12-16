package com.padc.shared.persistence.daos

import android.widget.GridLayout
import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.SpecialityVO

@Dao

interface SpecialitiesDao {
    @Query("SELECT * FROM specialitytable")
    @ColumnInfo
    fun getSpecialityList(): LiveData<List<SpecialityVO>>

    @Query("SELECT * FROM specialitytable WHERE id = :specialityID")
    @ColumnInfo
    fun getSpecialityById(specialityID : String) : LiveData<SpecialityVO>

    @Query("SELECT * FROM specialitytable WHERE specialityName = :specialityname")
    @ColumnInfo
    fun getSpecialQuestionBySpecialName(specialityname : String) : LiveData<SpecialityVO>


//    @Query("SELECT medicine FROM specialitytable WHERE specialityName = :specialityName")
//    @ColumnInfo
//    fun getMedicineBySpecialityName(specialityName: String) : LiveData<SpecialityVO>

    @Delete
    @ColumnInfo
    fun deleteSpeciality(Patient :SpecialityVO)

    @Query("DELETE FROM specialitytable")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertSpeicality(speicality: List<SpecialityVO>)
}