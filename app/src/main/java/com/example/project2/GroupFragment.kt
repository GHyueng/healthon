package com.example.project2

import FriendsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.databinding.FragmentGroupBinding
import com.example.project2.view.Friend
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupFragment : Fragment() {
    lateinit var binding: FragmentGroupBinding
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Friend")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // CoroutineScope를 사용하여 비동기 호출
        CoroutineScope(Dispatchers.Main).launch {
            // 리얼타임 데이터베이스에서 친구 데이터 가져오기
            val friendsList: List<Friend> = fetchFriendsData()

            // RecyclerView 설정
            binding.recFriends.layoutManager = LinearLayoutManager(requireContext())
            binding.recFriends.adapter = FriendsAdapter(friendsList)




        }
    }

    // 리얼타임 데이터베이스에서 친구 데이터를 가져와 리스트로 반환하는 함수
    private fun fetchFriendsData(): List<Friend> {
        val friendsList = mutableListOf<Friend>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 데이터가 변경될 때 호출되는 메서드
                friendsList.clear()

                for (snapshot in dataSnapshot.children) {

                    val friendId = snapshot.child("id").value as String?
                    //val friendId = snapshot.key // 데이터베이스에서 가져온 키를 id로 사용
                    //val friendId = snapshot.child("id") // 데이터베이스에서 가져온 키를 id로 사용
                    val friendDataSnapshot = snapshot.child("Data")
                    val friendNameSnapshot = snapshot.child("Name")
                    val friendExerciseTypeSnapshot = snapshot.child("exerciseType")
                    val friendNumberOfExercisesSnapshot = snapshot.child("numberOfExercises")

                    val friendData = friendDataSnapshot.getValue(String::class.java)
                    val friendName = friendNameSnapshot.getValue(String::class.java)
                    val friendExerciseType = friendExerciseTypeSnapshot.getValue(String::class.java)
                    val friendNumberOfExercises = friendNumberOfExercisesSnapshot.getValue(Int::class.java)

                    friendData?.let { data ->
                        friendName?.let { name ->
                            friendId?.let { id ->
                                friendExerciseType?.let { exerciseType ->
                                    friendNumberOfExercises?.let { numberOfExercises ->
                                        friendsList.add(
                                            Friend(
                                                id,
                                                name,
                                                data,
                                                exerciseType,
                                                numberOfExercises
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 데이터가 변경되었으므로 RecyclerView 갱신
                binding.recFriends.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // 데이터 가져오기가 실패했을 때 호출되는 메서드
                error.toException().printStackTrace()
            }
        })

        return friendsList
    }
}