package com.padc.the_health_care_app.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.shared.mvp.presenters.AbstractBasePresenter
import com.padc.the_health_care_app.mvp.presenters.MainPresenter
import com.padc.the_health_care_app.mvp.views.MainView

class MainPresenterImpl : MainPresenter,AbstractBasePresenter<MainView>() {
    override fun onUiReady(lifecycleOwner: LifecycleOwner,patientId : String) {
        mView?.showView()
    }
}