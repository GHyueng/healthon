package com.example.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.databinding.ListFriendsBinding

class PeoplesAdapter(val peoples: Array<People>) :RecyclerView.Adapter<PeoplesAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = peoples.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(peoples[position])

    }

    class Holder(private val binding: ListFriendsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(people: People){
            binding.imageView3.setImageResource( when(people.gender) {
                Egender.MALE -> R.drawable.men
                Egender.FEMALE -> R.drawable.woman
            })
            binding.txtName.text=people.name
        }
    }

}