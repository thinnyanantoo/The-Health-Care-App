package com.padc.the_health_care_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.PresriptionVO
import com.padc.the_health_care_app.R
import com.padc.the_health_care_app.viewholders.PrescribeMedicineViewHolder
import com.padc.the_health_care_app.viewholders.RecentlyViewHolder

class PrescribeMedicineAdapter : BaseRecyclerAdapter<PrescribeMedicineViewHolder,PresriptionVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescribeMedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_list_item_prescription_medicine,parent,false)
        return PrescribeMedicineViewHolder(view)
    }
}