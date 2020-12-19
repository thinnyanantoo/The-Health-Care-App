package com.padc.doctor.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.PrescriptionMedicinePresenter
import com.padc.doctor.mvp.views.PrescriptionMedicineView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class PrescriptionMedicinePresenterImpl : PrescriptionMedicinePresenter,
    AbstractBasePresenter<PrescriptionMedicineView>() {
    var conId =""
    private val mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReady(speicalityName: String,speicalityId : String,consultId: String,lifecycleOwner: LifecycleOwner) {
        conId = consultId
        mModel.getMedicineBySpecialityIdFromFirebaseAndSaveToDatabase(speicalityId,{},{})
        mModel.getMedicineBySpeciaityIdFromDatabase().observe(lifecycleOwner, Observer {
            mView?.showPrescriptionMedicine(it)
        })

    }

    override fun onTapConfirmPrescription(documentId: String, prescription: PresriptionVO) {
        mModel.addedToPrescription(documentId, prescription)
    }

    override fun onTapMedicine(medicine: String) {

    }
}