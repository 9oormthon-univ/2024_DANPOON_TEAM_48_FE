package com.example.a2024_danpoon_mesh

data class Chat(
    var chatroomId : Int,
    var chatroomName : String,
    var lastMessage : String,
    var lastMessageSender : String,
    var senderProfileImg : String?,
    var createAt : String,
    var participantCnt : Int
)
