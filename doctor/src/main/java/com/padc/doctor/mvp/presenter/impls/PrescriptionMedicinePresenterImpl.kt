package com.padc.doctor.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.mvp.presenter.PrescriptionMedicinePresenter
import com.padc.doctor.mvp.views.PrescriptionMedicineView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class PrescriptionMedicinePresenterImpl : PrescriptionMedicinePresenter,
    AbstractBasePresenter<PrescriptionMedicineView>() {
    private val mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReady(specialityVO: SpecialityVO, lifecycleOwner: LifecycleOwner) {
        mModel.getMedicineToPrescribe(specialityVO, specialityVO.specialityName, onSuccesss = {
            mView?.showPrescriptionMedicine(medicine = specialityVO.medicine)
        }, onError = {})

    }

    override fun onTapConfirmPrescription(documentId: String, prescription: PresriptionVO) {
        mModel.addedToPrescription(documentId, prescription)
    }
}