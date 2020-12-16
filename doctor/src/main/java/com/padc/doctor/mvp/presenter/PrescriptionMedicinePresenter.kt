package com.padc.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialityVO

interface PrescriptionMedicinePresenter {
    fun onUiReady(specialityVO: SpecialityVO,lifecycleOwner: LifecycleOwner)
    fun onTapConfirmPrescription(documentId : String,prescription : PresriptionVO)
}