package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.ConsultationRequestVO
import retrofit2.http.DELETE

interface ConsultationRequestDao {
    @Query("SELECT * FROM consultationRequest")
    fun getConsultationRequestLit(): LiveData<ConsultationRequestVO>

    @Query("SELECT * FROM consultationRequest WHERE id = :consultRequestID")
    fun getConsultationRequestById(consultRequestID: String) : LiveData<ConsultationRequestVO>

    @DELETE
    fun deleteConsultationRequest(consultRequest : ConsultationRequestVO)

    @Query("DELETE FROM consultationRequest")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetConsultationRequest(consultationRequest: List<ConsultationRequestVO>)
}