package com.padc.doctor.mvp.presenter.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.doctor.mvp.presenter.ShowSpecialQuestionPresenter
import com.padc.doctor.mvp.views.ShowSpecialQuestionView
import com.padc.shared.data.models.HealthCareModel
import com.padc.shared.data.models.impls.HealthCareModelImpl
import com.padc.shared.data.vos.SpecialityVO
import com.padc.shared.mvp.presenters.AbstractBasePresenter

class ShowSpecialQuestionPresenterImpl : ShowSpecialQuestionPresenter, AbstractBasePresenter<ShowSpecialQuestionView> () {
    var mModel : HealthCareModel = HealthCareModelImpl
    override fun onUiReady(specialityVO: SpecialityVO,lifecycleOwner: LifecycleOwner) {

        }
    }

