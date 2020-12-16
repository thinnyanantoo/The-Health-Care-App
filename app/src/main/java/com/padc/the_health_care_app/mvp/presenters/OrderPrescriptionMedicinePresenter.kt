package com.padc.the_health_care_app.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.CheckOutVO

interface OrderPrescriptionMedicinePresenter {
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapConfirmOrder(order : CheckOutVO)
}