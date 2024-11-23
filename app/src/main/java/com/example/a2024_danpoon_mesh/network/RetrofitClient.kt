import okhttp3.OkHttpClient
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://15.165.148.169"

    // Authorization Token (예: 로그인 후 액세스 토큰 저장)
    private var accessToken: String? = null

    // 인터셉터를 사용하여 Authorization 헤더를 추가
    private val interceptor = Interceptor { chain ->
        var request = chain.request()

        // 토큰이 있으면 Authorization 헤더 추가
        accessToken?.let {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
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

    // 액세스 토큰을 설정하는 함수
    fun setAccessToken(token: String) {
        accessToken = token
    }
}