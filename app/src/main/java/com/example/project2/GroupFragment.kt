package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.project2.databinding.FragmentGroupBinding
import com.example.project2.view.Friend

class GroupFragment : Fragment() {
    lateinit var binding: FragmentGroupBinding
    // 더미 친구 데이터 생성 실제 프로그램은 이렇게 되면 안됨 데이터 베이스 연동 해야됨
    val frinds= arrayOf(
        Friend("여빈","160cm 14kg"),
        Friend("근형","180cm 75kg")
    )
    //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentGroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {// 여기에서 뷰에 대한 추가 작업 수행
        super.onViewCreated(view, savedInstanceState)


        binding.recFriends.layoutManager= LinearLayoutManager(requireContext()) //수직 배치
        binding.recFriends.adapter=FriendsAdapter(frinds) // 중간 관리자 느낌


        // 예시: 버튼 클릭 이벤트 설정
        //binding.button.setOnClickListener {
        // 클릭 이벤트 처리 로직을 여기에 추가
        //}
    }
}


