package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.SpecialQuestionVO

@Dao
interface SpecialQuestionDao {
    @Query("SELECT * FROM specialQuestionTable")
    @ColumnInfo
    fun getSpecialQuestion(): LiveData<List<SpecialQuestionVO>>

    @Query("SELECT * FROM specialQuestionTable WHERE id = :specialityName")
    @ColumnInfo
    fun getSpecialityBySpecialityName(specialityName : String) : LiveData<SpecialQuestionVO>

    @Delete
    @ColumnInfo
    fun deleteSpecialQuestion(question : List<SpecialQuestionVO>)

    @Query("DELETE FROM specialQuestionTable")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertSpecialQuestion(patient: List<SpecialQuestionVO>)
}