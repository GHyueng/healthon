package com.example.project2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project2.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // GroupFragment에서 전달한 데이터 받기
        //val userId = arguments?.getString("userId")
        val id = arguments?.getString("id")
        val exerciseType = arguments?.getString("exerciseType")
        val numberOfExercises = arguments?.getInt("numberOfExercises", 0)

        // 받아온 데이터를 UI에 표시
        binding.userIdTextView.text = "User ID: $id"
        binding.exerciseTypeTextView.text = "Exercise Type: $exerciseType"
        binding.numberOfExercisesTextView.text = "Number of Exercises: $numberOfExercises"
    }
}