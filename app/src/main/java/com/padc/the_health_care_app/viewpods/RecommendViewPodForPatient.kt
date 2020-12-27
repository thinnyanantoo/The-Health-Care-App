package com.padc.the_health_care_app.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.padc.shared.data.vos.PresriptionVO
import kotlinx.android.synthetic.main.layout_recommend_medicine_view_pods.view.*

class RecommendViewPodForPatient @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpListener()
    }

    fun setPrescriptionData(prescription: List<PresriptionVO>) {
        Log.d("prescSize",prescription.size.toString())
        var data: String = ""
        if (prescription.isNotEmpty()) {
            for (item in prescription) {
                data += item.mname + "\n"
            }
        }
        tvMedicineName.text = data.toString()
    }

    fun setDelgate(delegate: Delegate) {
        mDelegate = delegate
    }

    private fun setUpListener(){
        btnOrder.setOnClickListener {
            mDelegate?.onTapOrderPrescription()
        }
    }


    interface Delegate {
        fun onTapOrderPrescription()
    }
}