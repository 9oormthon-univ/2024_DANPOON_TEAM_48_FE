data class postChatRoomResponse(
    var id: Int,
    var roomName: String,
    var creator: Creator,
    var postId: Int
)

data class Creator(
    var userId: Int,
    var major: String?,
    var email: String,
    var nickname: String,
    var kakaoId: Int,
    var profileImgUrl: String?,
    var content: String?,
    var meshScore: Int?,
    var portfolio: String?,
    var maincategories: List<String> = listOf(),
    var tools: List<String> = listOf(),
    var awards: List<String> = listOf(),
    var careers: List<String> = listOf()
)