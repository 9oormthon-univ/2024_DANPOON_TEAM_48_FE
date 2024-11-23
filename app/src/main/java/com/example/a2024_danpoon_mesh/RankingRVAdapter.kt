package com.example.a2024_danpoon_mesh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.a2024_danpoon_mesh.databinding.ItemRankingBinding

class RankingRVAdapter(
    private var rankingList: ArrayList<Ranking>
):RecyclerView.Adapter<RankingRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RankingRVAdapter.ViewHolder {
        val binding: ItemRankingBinding = ItemRankingBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingRVAdapter.ViewHolder, position: Int) {
        holder.bind(rankingList[position])
    }

    override fun getItemCount(): Int = rankingList.size

    inner class ViewHolder(val binding : ItemRankingBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ranking: Ranking) {
            binding.itemRankingRankTv.text = ranking.rank.toString()
            binding.itemRankingNameTv.text = ranking.nickname
            binding.itemRankingScoreTv.text = ranking.meshScore.toString()

            val picPreviewUrl = ranking.profileImg
            if (picPreviewUrl != null && picPreviewUrl.isNotEmpty()) {
                setImage(binding.itemRankingProfileImgIv, picPreviewUrl)
            }
        }
    }
    private fun setImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }

}