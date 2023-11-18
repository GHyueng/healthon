package com.example.project2

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.databinding.FragmentHomeBinding
import com.example.project2.model.UserData
import com.example.project2.view.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**리스트 설정*/
        userList = ArrayList()

        /**Adapter 설정*/
        userAdapter = UserAdapter(requireContext(), userList)

        /**recycler view Adapter 설정*/
        binding.mRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.mRecycler.adapter = userAdapter

        /**dialog 설정*/
        binding.addsBtn.setOnClickListener { addInfo() }
    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item, null)

        /**view 설정*/
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name: $names", "Mobile No. : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "Adding", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}
