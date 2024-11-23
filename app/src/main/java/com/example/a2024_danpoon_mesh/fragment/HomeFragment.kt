import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a2024_danpoon_mesh.PostingActivity
import com.example.a2024_danpoon_mesh.SearchActivity
import com.example.a2024_danpoon_mesh.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // SearchActivity 시작
        binding.homeSearchIv.setOnClickListener {
            val i = Intent(requireContext(), SearchActivity::class.java)
            startActivity(i)
        }

        // PostingActivity 시작을 버튼 클릭 시에 하도록 설정
        binding.homePublicContestBannerIv.setOnClickListener {
            val intent = Intent(requireActivity(), PostingActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}