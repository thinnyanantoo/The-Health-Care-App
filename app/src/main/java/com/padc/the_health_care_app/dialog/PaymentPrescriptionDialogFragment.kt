package com.padc.the_health_care_app.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.shared.data.vos.PresriptionVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.adapters.PrescribeMedicineAdapter
import com.padc.the_health_care_app.mvp.presenters.OrderPrescriptionMedicinePresenter
import com.padc.the_health_care_app.mvp.presenters.impls.OrderPrescriptionMedicinePresenterImpl
import com.padc.the_health_care_app.mvp.views.OrderPrescriptionMedicineView
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.payment_prescription_dialog.*

class PaymentPrescriptionDialogFragment : DialogFragment() {

    companion object {
        const val TAG_ADD_PAYMENT_PRESCRIPTION_DIALOG = "TAG_ADD_PAYMENT_PRESCRIPTION_DIALOG"
        const val BUNDLE_NAME = "BUNDLE_NAME"
        const val BUNDLE_DESCRIPTION = "BUNDLE_DESCRIPTION"
        const val BUNDLE_AMOUNT = "BUNDLE_AMOUNT"
        const val BITMAP_IMAGE = "BITMAP_IMAGE"
        const val BITMAP_ADDRESS = "BITMAP_ADDRESS"
        const val CONSULTATION_CHAT_ID = "CONSULTATION_CHAT_ID"
        const val SUB_TOTAL = "SUB_TOTAL"

        var medicinePrice: Int = 0
        var deliveryFee: Int = 3000
        fun newFragment(): PaymentPrescriptionDialogFragment {
            return PaymentPrescriptionDialogFragment()
        }
    }

    private var prescribeMedicineLists: ArrayList<PresriptionVO> = arrayListOf()
    private lateinit var mPresenter: OrderPrescriptionMedicinePresenter
    private lateinit var mPrescribeMedicineAdapter: PrescribeMedicineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.payment_prescription_dialog, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()
        setUpActionsListener()
        init()

    }

    private fun setUpRecyclerView() {
        rv_prescribeMedicine.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mPrescribeMedicineAdapter = PrescribeMedicineAdapter()
        rv_prescribeMedicine.adapter = mPrescribeMedicineAdapter
    }

    private fun init() {
        tvPatientFullAddress.text = arguments?.getString(BITMAP_ADDRESS)

    }

    private fun setUpActionsListener() {
        btnPaid.setOnClickListener {

            mPresenter.checkOutMedicine(
                txt_totalAmountValue.text.toString(),
                prescribeMedicineLists,
                arguments?.getString(CONSULTATION_CHAT_ID).toString()
            )
            dismiss()
        }
    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(OrderPrescriptionMedicinePresenterImpl::class.java)
        }

        medicinePrice = 0

        mPresenter.getPrescriptionLists()
            .observe(this, Observer {
                mPrescribeMedicineAdapter.setNewData(it.toMutableList())
                prescribeMedicineLists.addAll(it)

                for (i in it) {
                    medicinePrice += i.price.toInt()
                }

                tv_medicineSubTotal.text = medicinePrice.toString()
                txt_totalAmountValue.text = (medicinePrice + deliveryFee).toString()

            })

        mPresenter.getPatientFullAddress()
            .observe(this, Observer {
                if (it.isNotEmpty()) {
                    tvPatientFullAddress.text = it

                }

            })
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.FILL_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }


}
