package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.AddressVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.mvp.views.BaseView

interface OrderPrescriptionMedicineView : BaseView {

    fun displayPatientAddress(addressLists: List<AddressVO>)
    fun showPaymentDialog()
    fun displayFullAddress(fullAddress : String)
    fun displayPrescribeMedicineLists(medicineLists : List<PresriptionVO>)
    fun showEmptyAddressView()
    fun showRecyclerAddressView()
    fun selectedRecyclerAddress(address: AddressVO, previousPosition: Int)
    fun navigateToHomeFragment()
 }