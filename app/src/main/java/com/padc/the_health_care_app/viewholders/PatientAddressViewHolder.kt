package com.padc.the_health_care_app.viewholders
import android.graphics.Color
import android.text.Editable
import android.view.View
import com.padc.shared.data.vos.AddressVO
import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.delegates.PatietnAddressItemDelegate
import kotlinx.android.synthetic.main.rv_list_item_address.view.*

class PatientAddressViewHolder(itemView: View, var previsousPosition : Int, private val mDelegate: PatietnAddressItemDelegate) : BaseViewHolder<AddressVO>(itemView) {
    override fun bindData(data: AddressVO) {
       mData = data
        data?.let {
            itemView.ed_patient_address.text = Editable.Factory.getInstance().newEditable(data.fullAddress)
        }

        if(adapterPosition == previsousPosition){
            itemView.card_address.setBackgroundColor(Color.BLUE)
        }

        itemView.ed_patient_address.setOnClickListener {
            mDelegate.onTapAddress(data,adapterPosition)
        }
    }

    override fun position(id: Long) {
        TODO("Not yet implemented")
    }
}