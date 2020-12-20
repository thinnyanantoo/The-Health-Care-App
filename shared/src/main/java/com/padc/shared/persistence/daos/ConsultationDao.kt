package com.padc.shared.persistence.daos


import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.data.vos.GeneralQuestionVO

//@Dao
//interface ConsultationDao {
//    @Query("SELECT * FROM consultation")
//    @ColumnInfo
//    fun getconsultation(): LiveData<List<ConsultationVO>>
//
//    @Query("SELECT * FROM consultation WHERE id = :type")
//    @ColumnInfo
//    fun getConsultationByid(type : String) : LiveData<ConsultationVO>
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @ColumnInfo
//    fun insertConsultation(generalQuestionVO: List<ConsultationVO>)
//
//    @Delete
//    @ColumnInfo
//    fun deleteConsultation(question : List<ConsultationRequestVO>)
//
//    @Query("DELETE FROM consultationRequestTable")
//    @ColumnInfo
//    fun deleteAll()
//}