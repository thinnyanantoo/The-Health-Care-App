package com.padc.the_health_care_app.mvp.views

import com.padc.shared.data.vos.ConsultationVO
import com.padc.shared.mvp.views.BaseView

interface ConsultationHistoryView : BaseView{
    fun showConsultatioHistory(consultation : ConsultationVO)
}