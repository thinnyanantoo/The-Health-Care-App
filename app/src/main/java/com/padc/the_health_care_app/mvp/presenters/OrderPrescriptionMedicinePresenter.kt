package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.padc.shared.data.vos.AddressVO
import com.padc.shared.data.vos.CheckOutVO
import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.delegates.PatietnAddressItemDelegate
import com.padc.the_health_care_app.mvp.views.OrderPrescriptionMedicineView

interface OrderPrescriptionMedicinePresenter : BasePresenter<OrderPrescriptionMedicineView> , PatietnAddressItemDelegate{
    fun onUiReady(lifecycleOwner: LifecycleOwner,consutationID : String,patientId : String)
    fun onTapConfirmPayment(addressList : List<AddressVO>)
    fun checkOutMedicine(toatlPrice : String, prescribMedicineLists: ArrayList<PresriptionVO>, consultationId: String)
    fun getPrescriptionLists() : LiveData<List<PresriptionVO>>

    fun getPatientFullAddress() : LiveData<String>

  // fun onTapAddrss(addressVO: AddressVO, previousPosition : Int)
 //   fun showEmptyAddrssView()
}