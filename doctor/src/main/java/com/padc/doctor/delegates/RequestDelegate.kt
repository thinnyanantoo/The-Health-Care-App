package com.padc.doctor.delegates

import com.padc.shared.data.vos.ConsultationRequestVO

interface RequestDelegate  {
    fun onTapButtonAcceptInRequest(consultationRequestVO: ConsultationRequestVO)
    fun onTapButtonDeclineInRequest()
}