package com.padc.doctor.mvp.views

import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.views.BaseView

interface ShowSpecialQuestionView : BaseView {
    fun displaySpecialQuestion(specialityVO: SpecialityVO)
}