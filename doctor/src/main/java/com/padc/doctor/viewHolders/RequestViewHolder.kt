package com.padc.doctor.viewHolders

import android.view.View
import com.padc.doctor.R
import com.padc.shared.data.vos.ConsultationRequestVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.rv_request_list.view.*

class RequestViewHolder(itemView : View) : BaseViewHolder<ConsultationRequestVO>(itemView){
    override fun bindData(data: ConsultationRequestVO) {
        mData = data
        itemView.tvPatientName.text = data.patientVO?.pname
        itemView.tvBirthday.text = "မွေးနေ့ ${data.patientVO?.DOB}"
    }

}