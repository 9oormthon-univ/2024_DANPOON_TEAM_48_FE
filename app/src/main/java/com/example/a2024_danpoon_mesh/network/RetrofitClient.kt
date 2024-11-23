import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://15.165.148.169"

    // 액세스 토큰 (예: 로그인 후 저장)
    private var accessToken: String? = null

    // 인터셉터를 사용하여 Authorization 헤더 추가
    private val interceptor = Interceptor { chain ->
        var request = chain.request()

        // 액세스 토큰이 있을 경우 Authorization 헤더 추가
        accessToken?.let {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        } ?: run {
            // 액세스 토큰이 없을 경우 로그 출력
            println("AccessToken is null, no Authorization header will be added.")
        }

        chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)  // 커스텀 OkHttpClient 사용
            .build()
            .create(ApiService::class.java)
    }

    // 액세스 토큰 설정
    fun setAccessToken(token: String) {
        accessToken = token
    }

    // 액세스 토큰을 가져오는 함수 (필요 시)
    fun getAccessToken(): String? {
        return accessToken
    }

    // 토큰 삭제 (로그아웃 시 사용)
    fun clearAccessToken() {
        accessToken = null
    }
}