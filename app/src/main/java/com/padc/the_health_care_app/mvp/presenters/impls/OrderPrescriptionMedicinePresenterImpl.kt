package com.padc.the_health_care_app.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.CheckOutVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.mvp.presenters.OrderPrescriptionMedicinePresenter
import com.padc.the_health_care_app.mvp.views.OrderPrescriptionMedicineView

class OrderPrescriptionMedicinePresenterImpl : OrderPrescriptionMedicinePresenter, AbstractBasePresenter<OrderPrescriptionMedicineView>(){
    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun onTapConfirmOrder(order: CheckOutVO) {
        TODO("Not yet implemented")
    }
}