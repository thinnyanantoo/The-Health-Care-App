package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.GeneralQuestionVO

interface ConsultationRequestDao {
    @Query("SELECT * FROM generalQuestionTable")
    @ColumnInfo
    fun getGenerQuestion(): LiveData<ConsultationRequestVO>

    @Query("SELECT * FROM generalQuestionTable WHERE type = :type")
    @ColumnInfo
    fun getGeneralQuestionType(type : String) : LiveData<ConsultationRequestVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertGeneralQuestion(generalQuestionVO: List<ConsultationRequestVO>)

    @Delete
    @ColumnInfo
    fun deleteGeneralQuestion(question : List<ConsultationRequestVO>)
}