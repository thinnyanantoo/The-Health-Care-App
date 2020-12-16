package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ChatMessageVO(
    var imagemessage : String =  "",
    var sendAt : String = "",
    var sendBy : SendByVO = SendByVO(),
    var textMessage : String = ""
)

fun MutableMap<String, Any?>.convertToChatVO() : ChatMessageVO{
    val chatMessage = ChatMessageVO()
    chatMessage.imagemessage = this.get("image-message") as String
    chatMessage.sendAt = this.get("send-at")as String
    chatMessage.sendBy = this.get("send-by")as SendByVO
    chatMessage.textMessage = this.get("text-message")as String
    return chatMessage
}