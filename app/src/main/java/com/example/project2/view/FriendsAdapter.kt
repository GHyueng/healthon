// Korean full code of "package com.example.project2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.InformationFragment
import com.example.project2.R
import com.example.project2.databinding.ListFriendsBinding
import com.example.project2.view.Friend

class FriendsAdapter(val friends: List<Friend>) : RecyclerView.Adapter<FriendsAdapter.Holder>() {


    class Holder(private val binding: ListFriendsBinding) : RecyclerView.ViewHolder(binding.root) {
        // 아이템 가져오기
        fun bind(friend: Friend) {
            binding.name.text = friend.Name
            binding.data.text = friend.Data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListFriendsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentFriend = friends[position]
        holder.bind(currentFriend)

        // InformationFragment로 이동하는 코드 추가
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            // InformationFragment에 전달할 데이터 설정
            val informationFragment = InformationFragment()
            val bundle = Bundle()
            bundle.putString("id", currentFriend.id)
            bundle.putString("exerciseType", currentFriend.exerciseType)
            bundle.putInt("numberOfExercises", currentFriend.numberOfExercises)
            informationFragment.arguments = bundle

            // 프래그먼트 교체
            fragmentTransaction.replace(R.id.frg_nav, informationFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}
