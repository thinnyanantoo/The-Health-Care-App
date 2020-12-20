package com.padc.doctor.viewHolders

import android.view.View
import com.padc.doctor.delegates.MedicineDelegate
import com.padc.shared.data.vos.MedicineVO
import com.padc.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.rv_list_item_medicine.view.*
import kotlinx.android.synthetic.main.rv_list_show_question.view.*

class showMedicineViewHolder(itemView: View,var delegate : MedicineDelegate) :  BaseViewHolder<MedicineVO>(itemView){
    override fun bindData(data: MedicineVO) {
        mData = data
        itemView.tvMedicineName.text = data.mname

        data?.let {
           if(!data.isSelect){
               itemView.iv_add_medicine.visibility = View.VISIBLE
               itemView.iv_remove_medicine.visibility = View.GONE
           }
            else{
               itemView.iv_add_medicine.visibility = View.GONE
               itemView.iv_remove_medicine.visibility = View.VISIBLE
           }
        }


        itemView.iv_remove_medicine.setOnClickListener {
            delegate.onTapRemoveMedicine(data)
        }

        itemView.iv_add_medicine.setOnClickListener {
            delegate.onTapAddMedicine(data)
        }

    }

    override fun position(id: Long) {
        TODO("Not yet implemented")
    }
}
