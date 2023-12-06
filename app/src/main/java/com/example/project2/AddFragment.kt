package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.project2.databinding.FragmentAddBinding
import com.example.project2.model.Contacts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater,container,false )
        firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")

        binding.btnSend.setOnClickListener{
            saveData()
            findNavController().navigate(R.id.action_addFragment2_to_homeFragment)
        }

        return binding.root
    }

    private fun saveData() {
        val name = binding.edtName.text.toString()

        //name에 아무것도 없을 경우 오류
        if( name.isEmpty()) binding.edtName.error = "Write a Name"

        val contactId = firebaseRef.push().key!! //key값 설정
        val contacts = Contacts(contactId,name)

        firebaseRef.child(contactId).setValue(contacts)
            .addOnCompleteListener{
            Toast.makeText(context, "data stored", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener{
                Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}