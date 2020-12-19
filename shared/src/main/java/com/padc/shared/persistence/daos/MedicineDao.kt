package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.MedicineVO

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicine")
    @ColumnInfo
    fun getMedicine(): LiveData<List<MedicineVO>>

    @Query("SELECT * FROM medicine WHERE id = :medicineID")
    @ColumnInfo
    fun getMedicineById(medicineID : String) : LiveData<MedicineVO>

    @Delete
    @ColumnInfo
    fun deleteMedicine(medicine : MedicineVO)

    @Query("DELETE FROM medicine")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertMedicine(medicine: List<MedicineVO>)
}