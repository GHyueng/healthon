package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.databinding.FragmentHomeBinding
import com.example.project2.model.Contacts
import com.example.project2.model.RvContactsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactList: ArrayList<Contacts>
    private lateinit var firebaseRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false )

        binding.addsBtn.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment2)
        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
        contactList = arrayListOf()

        fetchData()
        binding.rvContacts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }

        return binding.root
    }

    private fun fetchData() {
        firebaseRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                if (snapshot.exists()) {
                    for (contactSnap in snapshot.children) {
                        val contacts = contactSnap.getValue(Contacts::class.java)
                        contactList.add(contacts!!)
                    }
                }
                val rvAdapter = RvContactsAdapter(contactList)
                binding.rvContacts.adapter = rvAdapter
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"error : $error", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
