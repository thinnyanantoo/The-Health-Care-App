package com.padc.doctor.delegates

import com.padc.shared.data.vos.MedicineVO

interface MedicineDelegate {
    fun onTapAddMedicine(medicineVO : MedicineVO)
    fun onTapRemoveMedicine(medicineVO: MedicineVO)
}