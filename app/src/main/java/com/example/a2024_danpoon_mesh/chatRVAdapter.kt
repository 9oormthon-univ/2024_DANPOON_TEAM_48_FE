package com.example.a2024_danpoon_mesh

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a2024_danpoon_mesh.databinding.ItemChattingBinding

class chatRVAdapter(private var chatroomList: ArrayList<Chat>):RecyclerView.Adapter<chatRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatRVAdapter.ViewHolder {
        val binding : ItemChattingBinding = ItemChattingBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: chatRVAdapter.ViewHolder, position: Int) {
        holder.bind(chatroomList[position])
    }

    override fun getItemCount(): Int = chatroomList.size

    inner class ViewHolder(val binding : ItemChattingBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(chat: Chat){
            val projectImgUrl = chat.senderProfileImg
            if (projectImgUrl != null && projectImgUrl.isNotEmpty()) {
                setImage(binding.itemChattingProfileIv, projectImgUrl)
            }

            binding.chatChattingMemberTv.text = chat.chatroomName
            binding.chatMemberCountTv.text = chat.participantCnt.toString()
            binding.chatMessageDateTv.text = chat.createAt
            binding.chatMessageContentTv.text = chat.lastMessage
        }
    }
    private fun setImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}