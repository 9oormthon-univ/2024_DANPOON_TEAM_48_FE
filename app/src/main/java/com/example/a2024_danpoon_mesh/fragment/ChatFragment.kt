//package com.example.mesh.Fragment
//
//import ApiService
//import UserProfileResponse
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.a2024_danpoon_mesh.Chat
//import com.example.a2024_danpoon_mesh.chatRVAdapter
//import com.example.a2024_danpoon_mesh.databinding.FragmentChatBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class ChatFragment : Fragment() {
//    private lateinit var binding: FragmentChatBinding
//    private lateinit var adapter: chatRVAdapter
//    private val chatroomList = ArrayList<Chat>()
//
//    // Retrofit 인스턴스 초기화
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://yourapiurl.com/") // API의 기본 URL로 변경
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val apiService: ApiService = retrofit.create(ApiService::class.java)
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentChatBinding.inflate(inflater, container, false)
//
//        initRecyclerView()
//
//        return binding.root
//    }
//
//    private fun initRecyclerView() {
//        adapter = chatRVAdapter(chatroomList)
//        binding.chatChattingRv.adapter = adapter
//        binding.chatChattingRv.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//    }
//
//    // 액세스 토큰을 가지고 사용자 프로필을 가져오기
//    fun getUserProfile(token: String) {
//        val call = apiService.getUserProfile(token)
//        call.enqueue(object : Callback<UserProfileResponse> {
//            override fun onResponse(
//                call: Call<UserProfileResponse>,
//                response: Response<UserProfileResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val userProfileResponse = response.body()
//                    val requesterId = userProfileResponse?.data // 데이터에서 requesterId를 추출
//
//                    // 이후 requesterId를 사용할 수 있음
//                    // 예를 들어 채팅방을 생성할 때 이 값을 사용할 수 있음
//                } else {
//                    // 실패 처리
//                    // 예: 서버 오류나 응답이 없을 때
//                }
//            }
//
//            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
//                // 오류 처리
//                // 예: 네트워크 오류
//            }
//        })
//    }
//}