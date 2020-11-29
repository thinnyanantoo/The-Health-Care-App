package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "checkout")
class CheckOutVO(
    @PrimaryKey
    var id: String = "",
    var address: String = "",
    var prescription: PresriptionVO = PresriptionVO(),
    var deliveryRoutine: DeliveryRoutineVO = DeliveryRoutineVO()

)