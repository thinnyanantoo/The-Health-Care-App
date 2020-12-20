package com.padc.the_health_care_app.viewholders

import android.view.View
import com.padc.shared.data.vos.ChatMessageVO
import com.padc.shared.viewholders.BaseViewHolder
import com.padc.the_health_care_app.delegates.ChatDelegate
import kotlinx.android.synthetic.main.rv_item_chat_by_patient.view.*

class ChatViewPatientViewHolder (itemView: View, val delegate : ChatDelegate) : BaseViewHolder<ChatMessageVO>(itemView){
    override fun bindData(data: ChatMessageVO) {
        data?.let {
            itemView.patientMessage?.text = it.textMessage
            itemView.patientSendTime?.text = it.sendAt
        } ?: "enter message"
               }


    override fun position(id: Long) {

    }
}
