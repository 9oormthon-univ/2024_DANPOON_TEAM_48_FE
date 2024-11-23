import com.example.a2024_danpoon_mesh.model.SignUpRequest
import com.example.a2024_danpoon_mesh.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/auth/signup/kakao")
    fun signUp(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

    @POST("/api/v1/auth/login/kakao") // 서버에서 사용자 정보를 가져오는 API 엔드포인트
    fun getUserProfile(
        @Header("Authorization") token: String // 액세스 토큰을 헤더로 전달
    ): Call<UserProfileResponse>
}

data class UserProfileResponse(
    val message: String,
    val statusCode: Int,
    val data: String
)