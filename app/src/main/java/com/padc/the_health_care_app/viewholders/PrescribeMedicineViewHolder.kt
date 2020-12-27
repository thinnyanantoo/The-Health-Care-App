package com.padc.the_health_care_app.viewholders

import android.view.View
import androidx.core.graphics.createBitmap
import com.padc.shared.data.vos.PresriptionVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.activity_check_out.view.*
import kotlinx.android.synthetic.main.rv_list_item_prescription_medicine.view.*

class PrescribeMedicineViewHolder(itemView: View): BaseViewHolder<PresriptionVO>(itemView) {
    override fun bindData(data: PresriptionVO) {
        mData = data
        itemView.tv_medicineName.text = data.mname
        itemView.tv_medicineAmount.text = data.price
        itemView.tv_medicineTab.text = data.count
        data?.let {
            itemView.tv_medicineTotalAmount
        }
    }

    override fun position(id: Long) {

    }

}