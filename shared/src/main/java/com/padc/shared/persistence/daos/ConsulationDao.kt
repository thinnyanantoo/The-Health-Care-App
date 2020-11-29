package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.ConsultationVO
import retrofit2.http.DELETE

@Dao
interface ConsulationDao {
    @Query("SELECT * FROM consultationTable")
    fun getConsultationLit(): LiveData<ConsultationVO>

    @Query("SELECT * FROM consultationTable WHERE id = :consultationID")
    fun getConsultationById(consultationID : String) : LiveData<ConsultationVO>

    @DELETE
    fun deleteConsultation(consultation : ConsultationVO)

    @Query("DELETE FROM consultationTable")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetConsultation(consultation: List<ConsultationVO>)
}