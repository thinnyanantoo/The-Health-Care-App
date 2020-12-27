package com.padc.the_health_care_app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.api.Distribution
import com.padc.shared.activity.BaseActivity
import com.padc.shared.data.vos.AddressVO
import com.padc.shared.data.vos.PresriptionVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.adapters.PatientAddressAdapter
import com.padc.the_health_care_app.adapters.PrescribeMedicineAdapter
import com.padc.the_health_care_app.dialog.PaymentPrescriptionDialogFragment
import com.padc.the_health_care_app.dialog.PaymentPrescriptionDialogFragment.Companion.BITMAP_ADDRESS
import com.padc.the_health_care_app.dialog.PaymentPrescriptionDialogFragment.Companion.medicinePrice
import com.padc.the_health_care_app.mvp.presenters.OrderPrescriptionMedicinePresenter
import com.padc.the_health_care_app.mvp.presenters.impls.OrderPrescriptionMedicinePresenterImpl
import com.padc.the_health_care_app.mvp.views.OrderPrescriptionMedicineView
import com.padc.the_health_care_app.utils.SessionManager
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.payment_prescription_dialog.*
import java.nio.BufferUnderflowException

class CheckOutActivity : BaseActivity(),OrderPrescriptionMedicineView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        setUpPresenter()
        setUpRcyclerView()
        setUpListener()

        mPresentr.onUiReady(this, SessionManager.request_id_for_patient.toString(),SessionManager.patient_id.toString())
    }

    companion object {
        val PARAMCONSULTATIONID = "CONSULTATIONID"
        fun newIntent(context: Context,conId: String): Intent {
            val intent = Intent(context,CheckOutActivity::class.java)
            intent.putExtra(PARAMCONSULTATIONID,conId)
            return intent
        }
    }

    private var state : String? = null
    private var township : String? = null
    var price: Int = 0
    private lateinit var mAddress: String
    private var mPreviousPosition : Int = -1
    private lateinit var mAddressLists : List<AddressVO>

    private lateinit var mPresentr : OrderPrescriptionMedicinePresenter
    private lateinit var mAddressAdaptr : PatientAddressAdapter
    private lateinit var mPrscribeMedicineAdapter : PrescribeMedicineAdapter


    private fun setUpPresenter(){
        mPresentr = ViewModelProviders.of(this).get(OrderPrescriptionMedicinePresenterImpl::class.java)
        mPresentr.initPresenter(this)
    }

    private fun setUpListener(){
        state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                state = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        township_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                township = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        card_add_address.setOnClickListener {
            card_add_address.visibility = View.VISIBLE
            recycler_address.visibility = View.GONE
        }

        btn_order.setOnClickListener {
            val addressLists : ArrayList<AddressVO> = arrayListOf()
            val patientAddress = AddressVO(state.toString(),"",township.toString(),ed_fullAddress.text.toString())
            addressLists.add(patientAddress)

            mPresentr.onTapConfirmPayment(addressLists)

        }

        btn_order_recycler.setOnClickListener {
            val paymentDialog = PaymentPrescriptionDialogFragment.newFragment()
            val bundle = Bundle()
            paymentDialog.arguments = bundle
            bundle.putString(
                PaymentPrescriptionDialogFragment.BITMAP_ADDRESS,ed_fullAddress.text.toString())
            paymentDialog.arguments = bundle
            supportFragmentManager?.let {
                paymentDialog.show(
                    it,
                    PaymentPrescriptionDialogFragment.TAG_ADD_PAYMENT_PRESCRIPTION_DIALOG
                )
            }
        }
    }

    private fun setUpRcyclerView(){
        rv_fullAddress.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mAddressAdaptr = PatientAddressAdapter(mPresentr,mPreviousPosition)
        rv_fullAddress.adapter = mAddressAdaptr

        rvPrescribeMedicine.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mPrscribeMedicineAdapter = PrescribeMedicineAdapter()
        rvPrescribeMedicine.adapter = mPrscribeMedicineAdapter
    }

    override fun displayPatientAddress(addressLists: List<AddressVO>) {
        Log.d("addressList",addressLists.size.toString())
        if(addressLists.isNotEmpty()) {
            mAddressLists = addressLists
        }

        if(addressLists.isNotEmpty()){
            recycler_address.visibility = View.VISIBLE
            layout_address.visibility = View.GONE
        }
        else {
            recycler_address.visibility = View.GONE
            layout_address.visibility = View.VISIBLE
        }

        mAddressAdaptr.setNewData(addressLists.toMutableList())
    }

    override fun showPaymentDialog() {
        val paymentDialog = PaymentPrescriptionDialogFragment.newFragment()
        val bundle  = Bundle()
        paymentDialog.arguments = bundle
        bundle.putString(BITMAP_ADDRESS, ed_fullAddress.text.toString())

        paymentDialog.arguments = bundle

        supportFragmentManager?.let {
            paymentDialog.show(
                it,
                PaymentPrescriptionDialogFragment.TAG_ADD_PAYMENT_PRESCRIPTION_DIALOG
            )
        }
    }

    override fun displayFullAddress(fullAddress: String) {
        Log.d("FullAddress", fullAddress)
    }

    override fun displayPrescribeMedicineLists(medicineLists: List<PresriptionVO>) {
        Log.d("medicineLists", medicineLists.size.toString())
        mPrscribeMedicineAdapter.setNewData(medicineLists.toMutableList())

        for (i in medicineLists){
            medicinePrice += i.price.toInt()
        }
        tv_medicineTotalAmount.text = medicinePrice.toString()

    }


    override fun showEmptyAddressView() {
        layout_address.visibility = View.VISIBLE
        recycler_address.visibility = View.GONE
    }

    override fun showRecyclerAddressView() {
        recycler_address.visibility = View.VISIBLE
        layout_address.visibility = View.GONE
    }

    override fun selectedRecyclerAddress(address: AddressVO, previousPosition: Int) {
       mAddress = address?.fullAddress.toString()
        mPreviousPosition = previousPosition
        mAddressAdaptr.setNewData(mAddressLists.toMutableList())
        rv_fullAddress?.adapter = mAddressAdaptr
    }

    override fun navigateToHomeFragment() {
        startActivity(MainActivity.newIntentTwo(this))
        finish()
    }
}