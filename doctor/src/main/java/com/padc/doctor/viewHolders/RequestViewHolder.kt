package com.padc.doctor.viewHolders

import android.util.Log
import android.view.View
import com.padc.doctor.R
import com.padc.doctor.delegates.RequestDelegate
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.rv_request_list.view.*

class RequestViewHolder(itemView : View, val delegate : RequestDelegate) : BaseViewHolder<ConsultationRequestVO>(itemView){
    override fun bindData(data: ConsultationRequestVO) {
        mData = data
        itemView.tvPatientName.text = data.patientVO?.pname.toString()
        itemView.tvBirthday.text = data.patientVO?.DOB.toString()

    }

    init {
        itemView.btnAccept.setOnClickListener {
            mData?.let{
                Log.d("TOUCH","ON TOUCH ON ACCEPT BUTTON")
                delegate.onTapButtonAcceptInRequest(it)
            }

        }
    }

    override fun position(id: Long) {
        TODO("Not yet implemented")
    }

}