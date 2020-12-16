package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.GeneralQuestionVO

@Dao
interface GeneralQuestonDao {
    @Query("SELECT * FROM generalQuestionTable")
    @ColumnInfo
    fun getGeneralQuestion(): LiveData<GeneralQuestionVO>

    @Query("SELECT * FROM generalQuestionTable WHERE type = :type")
    @ColumnInfo
    fun getGeneralQuestionType(type : String) : LiveData<GeneralQuestionVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertGeneralQuestion(generalQuestionVO: List<GeneralQuestionVO>)

    @Delete
    @ColumnInfo
    fun deleteGeneralQuestion(question : List<GeneralQuestionVO>)

}