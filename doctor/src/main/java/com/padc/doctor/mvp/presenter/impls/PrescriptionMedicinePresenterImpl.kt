package com.padc.doctor.mvp.presenter.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.PrescriptionMedicinePresenter
import com.padc.doctor.mvp.views.PrescriptionMedicineView
import com.padc.doctor.utils.SessionManager
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class PrescriptionMedicinePresenterImpl : PrescriptionMedicinePresenter,
    AbstractBasePresenter<PrescriptionMedicineView>() {
    var conId =""

    private val mPrescriptionLists = MutableLiveData<List<PresriptionVO>>()
    private val medicineLists: ArrayList<MedicineVO> = arrayListOf()
    private var prescriptionLists: ArrayList<PresriptionVO> = arrayListOf()

    private val mModel: HealthCareModel = HealthCareModelImpl
    override fun onUiReady(speicalityName: String,speicalityId : String,consultId: String,lifecycleOwner: LifecycleOwner) {
        conId = consultId
        mModel.getMedicineBySpecialityIdFromFirebaseAndSaveToDatabase(speicalityId,{},{})
        mModel.getMedicineBySpeciaityIdFromDatabase().observe(lifecycleOwner, Observer {
            medicineLists.addAll(it)
            mView?.showPrescriptionMedicine(it)
        })

        mModel.getConsultationById(consultId,onSuccess = {
            mView?.displayPatientRequestData(
                it
            )
        }
            , onFailure = {})

    }

    override fun onTapAddMedicine(medicineVO: MedicineVO) {
        mView?.showPrescribeMedicineDialog(medicineVO)
    }

    override fun onTapRemoveMedicine(medicineVO: MedicineVO) {
       for(i in medicineLists){
           i.mname.let {
               medicineName ->
               if(medicineName.equals(medicineVO.mname)){
                   i.isSelect = false
                   mView?.showPrescriptionMedicine(medicineLists)
               }
           }
       }
    }

    override fun onTapStopConsultation(consultationVO: ConsultationVO) {
        if(prescriptionLists.isNotEmpty()){
            mModel.finishConsultation(consultationVO,prescriptionLists,
                onSuccess = {
                mView?.navigatetoChatScreen()
            }, onError = {

                })
        }
    }

    override fun addToPrescribeMedicineList(prescription: PresriptionVO) {
        prescriptionLists.add(prescription)
Log.d("prescriptionSize",prescriptionLists.size.toString())
         mModel.getMedicineBySpeciality(SessionManager.speciality_id.toString(),
             onSuccess = {
             Log.d("mediciSize",medicineLists.size.toString())
             medicineLists.forEach{ medicine ->
                 if(prescription.mname == medicine.mname){
                     medicine.isSelect = true
                 }
                 mView?.showPrescriptionMedicine(
                     medicineLists
                 )
             }
         }, onFailure = {})

    }
}