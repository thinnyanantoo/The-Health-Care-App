package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc.shared.data.vos.CheckOutVO
import retrofit2.http.DELETE

@Dao
interface CheckOutDao {
    @Query("SELECT * FROM checkout")
    fun getCheckOutList(): LiveData<List<CheckOutVO>>

    @Query("SELECT * FROM checkout WHERE id = :checkOutID")
    fun getCheckOutById(checkOutID : String) : LiveData<CheckOutVO>

    @DELETE
    fun deleteCheckout(checkOut : CheckOutVO)

    @Query("DELETE FROM checkout")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckout(checkout: CheckOutVO)
}