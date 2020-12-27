package com.padc.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.padc.shared.data.vos.PresriptionVO
import kotlinx.android.synthetic.main.layout_recommend_medicine_view_pods.view.*
import kotlinx.android.synthetic.main.rv_list_item_medicine.view.*

class RecommendMedicineViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(
    context, attrs, defStyleAttr
) {

    private var mDelegate : Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    fun setPrescriptionData(prescription : List<PresriptionVO>,docotrId: String){

        var data : String = ""
        if(prescription.isNotEmpty())
        {
            for(item in prescription)
            {
                data +=item.mname +"\n"
            }
        }
        tvMedicineList.text = data.toString()
    }
}

interface Delegate {}