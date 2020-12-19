package com.padc.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.padc.doctor.R
import com.padc.doctor.delegates.MedicineDelegate
import com.padc.doctor.viewHolders.ShowQuestionViewHolder
import com.padc.doctor.viewHolders.showMedicineViewHolder
import com.padc.shared.activity.BaseActivity
import com.padc.shared.adapter.BaseRecyclerAdapter
import com.padc.shared.data.vos.MedicineVO

class ShowMedicineAdapter(private val delegate: MedicineDelegate) : BaseRecyclerAdapter<showMedicineViewHolder,MedicineVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): showMedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_list_item_medicine,parent,false)
        return showMedicineViewHolder(view,delegate)
    }
}