package com.padc.doctor.mvp.views

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.views.BaseView

interface PrescriptionMedicineView : BaseView {
    fun showPrescriptionMedicine(medicine: List<MedicineVO>)
}
