package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.MedicineVO
import retrofit2.http.DELETE

interface MedicineDao {
    @Query("SELECT * FROM medicine")
    fun getMedicine(): LiveData<MedicineVO>

    @Query("SELECT * FROM checkout WHERE id = :medicineID")
    fun getMedicineById(medicineID : String) : LiveData<MedicineVO>

    @DELETE
    fun deleteMedicine(checkOut : MedicineVO)

    @Query("DELETE FROM medicine")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicine(medicine: List<MedicineVO>)
}