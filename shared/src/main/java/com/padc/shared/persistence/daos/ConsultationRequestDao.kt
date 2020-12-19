package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.GeneralQuestionVO

@Dao
interface ConsultationRequestDao {
    @Query("SELECT * FROM consultationRequestTable")
    @ColumnInfo
    fun getconsultationRequest(): LiveData<List<ConsultationRequestVO>>

    @Query("SELECT * FROM consultationRequestTable WHERE id = :type")
    @ColumnInfo
    fun getConsultationRequestByID(type : String) : LiveData<ConsultationRequestVO>

    @Query("SELECT * FROM consultationRequestTable WHERE status = :status")
    fun getConsultationByStatus(status: String) : LiveData<ConsultationRequestVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertConsultationRequest(generalQuestionVO: List<ConsultationRequestVO>)

    @Delete
    @ColumnInfo
    fun deleteConsultationRequest(question : List<ConsultationRequestVO>)

    @Query("DELETE FROM consultationRequestTable")
    @ColumnInfo
    fun deleteAll()
}