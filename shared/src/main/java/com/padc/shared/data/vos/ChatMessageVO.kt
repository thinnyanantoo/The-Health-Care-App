package com.padc.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class ChatMessageVO(
    var id : String ? = "",
    var imagemessage : String? =  "",
    var sendAt : String? = "",
    var sendBy : String? = "",
    var photo : String? = "",
    var time : String? = "",
    var textMessage : String? = ""
)

fun MutableMap<String, Any>?.convertToChatVO() : ChatMessageVO?{
    val chatMessage = ChatMessageVO()
    chatMessage.id = this?.get("id") as String
    chatMessage.imagemessage = this?.get("image-message") as String?
    chatMessage.sendAt = this?.get("send-at")as String
    chatMessage.sendBy = this.get("send-by")as String
    chatMessage.time = this?.get("time")as String?
    chatMessage.textMessage = this.get("text-message")as String
    chatMessage.photo = this.get("photo")as String?
    return chatMessage
}