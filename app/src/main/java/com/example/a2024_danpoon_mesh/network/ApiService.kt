import com.example.a2024_danpoon_mesh.model.PostingRequest
import com.example.a2024_danpoon_mesh.model.PostingResponse
import com.example.a2024_danpoon_mesh.model.SignUpRequest
import com.example.a2024_danpoon_mesh.model.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @POST("/api/v1/auth/signup/kakao")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

    @POST("/api/v1/auth/login/kakao") // 서버에서 사용자 정보를 가져오는 API 엔드포인트
    fun getUserProfile(
        @Header("Authorization") token: String // 액세스 토큰을 헤더로 전달
    ): Call<UserProfileResponse>

    @POST("/api/v1/chat/room")
    fun postChatRoom(
        @Query("requesterId") requesterId: Int,
        @Query("postId") postId: Int
    ): Call<postChatRoomResponse>

    @Multipart
    @POST("/api/v1/posts")
    fun submitPosting(
        @Part("postRequest") postRequest: RequestBody, // JSON 데이터
        @Part projectFile: MultipartBody.Part?,       // 첨부 파일
        @Part projectImage: MultipartBody.Part?       // 이미지 파일
    ): Response<PostingResponse>
}

data class UserProfileResponse(
    val message: String,
    val statusCode: Int,
    val data: String
)