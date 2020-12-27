package com.padc.the_health_care_app.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.*
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.shared.mvp.presenters.BasePresenter
import com.padc.the_health_care_app.mvp.presenters.OrderPrescriptionMedicinePresenter
import com.padc.the_health_care_app.mvp.views.OrderPrescriptionMedicineView
import com.padc.the_health_care_app.utils.SessionManager
import java.util.*
import kotlin.collections.ArrayList

class OrderPrescriptionMedicinePresenterImpl : OrderPrescriptionMedicinePresenter,
    AbstractBasePresenter<OrderPrescriptionMedicineView>() {
    private var mModel: HealthCareModel = HealthCareModelImpl
    private var mPrescriptionDataList = MutableLiveData<List<PresriptionVO>>()
    private var mPatientFullAddress = MutableLiveData<String>()

    private var mAddressLists: ArrayList<AddressVO> = arrayListOf()
    lateinit var mConsultationId: String

    override fun onUiReady(
        lifecycleOwner: LifecycleOwner,
        consutationID: String,
        patientId: String
    ) {
        mConsultationId = consutationID
        mModel.getPatientById(patientId).observe(lifecycleOwner, Observer {
          //  mAddressLists.addAll(it.address)
            mView?.displayPatientAddress(mAddressLists)
        })

        mModel.getPrescription(consultationId = mConsultationId, onSuccess = {
            mView?.displayPrescribeMedicineLists(it)
            mPrescriptionDataList.value = it
        },
            onFailure = {})
    }


    override fun onTapConfirmPayment(addressList: List<AddressVO>) {
        mAddressLists.addAll(addressList)
        val patientVO = PatientVO(
            id = SessionManager.patient_id.toString(),
            pname = SessionManager.patient_name.toString(),
            height = SessionManager.patient_height.toString(),
            weight = SessionManager.patient_weight.toString(),
            bloodPressure = SessionManager.patient_bloodPressure.toString(),
            bloodType = SessionManager.patient_bloodType.toString(),
            allergicMedicine = SessionManager.patient_dateAllegic.toString(),
            DOB = SessionManager.patient_dateOfBirth.toString(),
            email = SessionManager.patient_email.toString(),
            address = mAddressLists
        )

        mModel.registerNewPatient(patientVO = patientVO, onSuccess = {
            mView?.showPaymentDialog()
        }, onFailure = {})

    }

    override fun checkOutMedicine(
        toatlPrice: String,
        prescribMedicineLists: ArrayList<PresriptionVO>,
        consultationId: String
    ) {
        mModel.getConsultationById(mConsultationId, onSuccess = {}, onFailure = {})

        val patientVO = PatientVO(
            id = SessionManager.patient_id.toString(),
            pname = SessionManager.patient_name.toString(),
            height = SessionManager.patient_height.toString(),
            weight = SessionManager.patient_weight.toString(),
            address = mAddressLists,
            bloodPressure = SessionManager.patient_bloodPressure.toString(),
            bloodType = SessionManager.patient_bloodType.toString(),
            allergicMedicine = SessionManager.patient_dateAllegic.toString(),
            DOB = SessionManager.patient_dateOfBirth.toString(),
            email = SessionManager.patient_email.toString()
        )

        mModel.getConsultationById(consultationId, onSuccess = {
            it?.let {
                val checkOut = CheckOutVO(
                    UUID.randomUUID().toString(),
                    address = "",
                    deliveryRoutine = DeliveryRoutineVO(),
                    patientVO = patientVO,
                    doctorVO = it.doctor,
                    prescription = prescribMedicineLists,
                    totalPrice = toatlPrice
                )

                mModel.addToCheckOut(checkOut,onSuccess = {
                        mView?.navigateToHomeFragment()
                },onFailure = {})
            }
        },onFailure = {})
    }

    override fun getPrescriptionLists(): LiveData<List<PresriptionVO>> {
        return mPrescriptionDataList
    }

    override fun getPatientFullAddress(): LiveData<String> {
        return mPatientFullAddress
    }

//    override fun onTapAddrss(addressVO: AddressVO, previousPosition: Int) {
//        mPatientFullAddress.value = addressVO.fullAddress
//        mView?.selectedRecyclerAddress(addressVO,previousPosition)
//    }

//    override fun showEmptyAddrssView() {
//        mView?.showEmptyAddressView()
//    }

    override fun onTapAddress(address: AddressVO, previousPosition: Int) {
        mPatientFullAddress.value = address.fullAddress
        mView?.selectedRecyclerAddress(address,previousPosition)
    }

    override fun showEmptyAddressView() {
        mView?.showEmptyAddressView()
    }

    override fun showRecyclerAddresView() {
        mView?.showRecyclerAddressView()
    }

}