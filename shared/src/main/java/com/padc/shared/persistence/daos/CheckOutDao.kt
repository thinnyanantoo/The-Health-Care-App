package com.padc.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padc.shared.data.vos.CheckOutVO

@Dao
interface CheckOutDao {
    @Query("SELECT * FROM checkout")
    @ColumnInfo
    fun getCheckOutList(): LiveData<List<CheckOutVO>>

    @Query("SELECT * FROM checkout WHERE id = :checkOutID")
    @ColumnInfo
    fun getCheckOutById(checkOutID : String) : LiveData<CheckOutVO>

    @Delete
    @ColumnInfo
    fun deleteCheckout(checkOut : CheckOutVO)

    @Query("DELETE FROM checkout")
    @ColumnInfo
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @ColumnInfo
    fun insertCheckout(checkout: CheckOutVO)
}