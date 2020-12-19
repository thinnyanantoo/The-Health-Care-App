package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.doctor.delegates.MedicineDelegate
import com.padc.doctor.mvp.views.PrescriptionMedicineView
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.BasePresenter

interface PrescriptionMedicinePresenter: BasePresenter<PrescriptionMedicineView>, MedicineDelegate {
    fun onUiReady(speicalityName: String,speicalityId : String,consultId: String,lifecycleOwner: LifecycleOwner)
    fun onTapConfirmPrescription(documentId : String,prescription : PresriptionVO)
}