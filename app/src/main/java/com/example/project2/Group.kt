package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.project2.databinding.FragmentFriendsBinding
import com.example.project2.databinding.FragmentGroupBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Group.newInstance] factory method to
 * create an instance of this fragment.
 */
class Group : Fragment() {
    var binding: FragmentGroupBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentGroupBinding.inflate(inflater)
        return binding?.root
    }
    //생성 후 작업은 여기서
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)// 기본

        binding?.groupbtn1?.setOnClickListener{
            findNavController().navigate(R.id.action_group2_to_friends)
        }
    }

}