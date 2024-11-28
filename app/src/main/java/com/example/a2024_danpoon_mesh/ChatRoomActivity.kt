//package com.example.a2024_danpoon_mesh
//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.a2024_danpoon_mesh.network.RetrofitClient.apiService
//import postChatRoomResponse
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class ChatRoomActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chat_room)
//
//        // 예시로, 채팅방을 생성하는 데 필요한 값들이 있다고 가정
//        val requesterId = 1 // 실제 값으로 대체
//        val postId = 123 // 실제 값으로 대체
//
//        // 채팅방 생성 메서드 호출
//        createChatRoom(requesterId, postId)
//    }
//
//    fun createChatRoom(requesterId: Int, postId: Int) {
//        val call = apiService.postChatRoom(requesterId, postId)
//        call.enqueue(object : Callback<postChatRoomResponse> {
//            override fun onResponse(
//                call: Call<postChatRoomResponse>,
//                response: Response<postChatRoomResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val chatRoomResponse = response.body()
//                    // 데이터 처리
//                    val chatRoomId = chatRoomResponse?.id
//                    val roomName = chatRoomResponse?.roomName
//                    val creator = chatRoomResponse?.creator
//                    val postId = chatRoomResponse?.postId
//
//                    // 채팅방 생성 성공 시 UI 처리
//                    if (creator != null) {
//                        Toast.makeText(
//                            applicationContext,
//                            "채팅방 생성 성공! 방 이름: $roomName",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            "채팅방 생성 실패: 생성자 정보 누락",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } else {
//                    // 응답 실패 처리
//                    Toast.makeText(
//                        applicationContext,
//                        "채팅방 생성 실패: ${response.message()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<postChatRoomResponse>, t: Throwable) {
//                // 네트워크 오류나 예외 처리
//                Toast.makeText(
//                    applicationContext,
//                    "네트워크 오류: ${t.localizedMessage}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//    }
//}