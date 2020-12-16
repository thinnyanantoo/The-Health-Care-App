package com.padc.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padc.shared.persistence.typeConverters.AddressTypeConverters

@IgnoreExtraProperties
@Entity(tableName = "patient")
@TypeConverters(AddressTypeConverters::class)
data class PatientVO(
    @PrimaryKey
    var id: String = "",
    var photo: String? = "",
    var pname: String? = "",
    var email : String? = "",
    var password : String? = "",
    var weight : String? = "",
    var height : String? = "",
    var DOB : String? = "",
    var allergicMedicine: String? = "",
    var bloodPressure : String? = "",
    var bloodType : String? = "",
    var deviceId : String ? = ""
  //  var address: AddressVO ? = null
  //  var oneTimeGeneralQuestion: OnetimeGeneralQuestionVO = OnetimeGeneralQuestionVO()
//    var recentlyDoctor: RecentlyDoctorVo? = null
)


