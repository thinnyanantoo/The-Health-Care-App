package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.CaseSummaryVO


@Dao
interface CaseSummaryDao {
    @Query("SELECT * FROM caseSummaryTable")
    @ColumnInfo
    fun getCaseSummary(): LiveData<List<CaseSummaryVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertCaseSummary(generalQuestionVO: List<CaseSummaryVO>)

    @Delete
    @ColumnInfo
    fun deleteCaseSummary(question: List<CaseSummaryVO>)

    @Query("DELETE FROM caseSummaryTable")
    @ColumnInfo
    fun deleteAll()


}