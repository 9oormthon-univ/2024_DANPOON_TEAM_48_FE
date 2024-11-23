package com.example.a2024_danpoon_mesh

import android.app.appsearch.SearchResult
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2024_danpoon_mesh.databinding.ItemSearchResultBinding

class SearchResultRVAdapter(private var resultList: ArrayList<com.example.a2024_danpoon_mesh.SearchResult>):RecyclerView.Adapter<SearchResultRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultRVAdapter.ViewHolder {
        val binding : ItemSearchResultBinding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultRVAdapter.ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    override fun getItemCount(): Int = resultList.size

    inner class ViewHolder(val binding : ItemSearchResultBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(searchResult: com.example.a2024_danpoon_mesh.SearchResult){
            binding.itemSearchContentTv.text = searchResult.postTitle
            binding.itemSearchResultNicknameTv.text = searchResult.nickname
            binding.searchResultViewCountTv.text = searchResult.views.toString()

            val status = searchResult.status
            when (status) {
                "모집중" -> {
                    binding.itemSearchResultStateIv.setImageResource(R.drawable.ic_recruiting)
                }
                "모집완료" -> {
                    binding.itemSearchResultStateIv.setImageResource(R.drawable.ic_recruiting_completed)
                }
                "활동중" -> {
                    binding.itemSearchResultStateIv.setImageResource(R.drawable.ic_active)
                }
                "활동완료" -> {
                    binding.itemSearchResultStateIv.setImageResource(R.drawable.ic_active_completed)
                }
            }

            val projectImgUrl = searchResult.projectImg
            if (projectImgUrl != null && projectImgUrl.isNotEmpty()) {
                setImage(binding.itemSearchResultProjectImgIv, projectImgUrl)
            }

            val profileImgUrl = searchResult.profileImg
            if (profileImgUrl != null && profileImgUrl.isNotEmpty()) {
                setImage(binding.itemSearchResultProfileImgIv, profileImgUrl)
            }
        }
    }
    private fun setImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}