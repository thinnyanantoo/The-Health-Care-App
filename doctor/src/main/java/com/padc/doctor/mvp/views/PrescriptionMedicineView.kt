package com.padc.doctor.mvp.views

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.views.BaseView

interface PrescriptionMedicineView : BaseView {
    fun showPrescriptionMedicine(medicine: List<MedicineVO>)

    fun showPrescribeMedicineDialog(medicineVO: MedicineVO)

    fun showtext(text : String)

    fun navigateToSplashScreen()

    fun displayPatientRequestData(data: ConsultationVO)

    fun displayPrescriptionLists(prescriptionVO : PresriptionVO)

    fun navigatetoChatScreen()
}
