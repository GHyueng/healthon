package com.example.project2

import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.databinding.ListFriendsBinding
import com.example.project2.view.Friend

class FriendsAdapter(val friends: Array<Friend>) : RecyclerView.Adapter<FriendsAdapter.Holder>() {
    class Holder(private val binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(friend: Friend){
            binding.name.text=friend.Name
            binding.data.text=friend.Data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount()= friends.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(friends[position])
    }

}