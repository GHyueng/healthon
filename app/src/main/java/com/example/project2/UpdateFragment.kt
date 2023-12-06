package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project2.databinding.FragmentAddBinding
import com.example.project2.databinding.FragmentUpdateBinding
import com.example.project2.model.Contacts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateFragment : Fragment() {

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args : UpdateFragmentArgs by navArgs()
    private lateinit var firebaseRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        firebaseRef  = FirebaseDatabase.getInstance().getReference("contacts")

        binding.apply {
            edtUpdateName.setText(args.name)
            btnUpdate.setOnClickListener{
                updateData()
                findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }
        }

        return binding.root
    }

    private fun updateData() {
        val name = binding.edtUpdateName.text.toString()
        val contacts = Contacts(args.id, name)

        firebaseRef.child(args.id).setValue(contacts)
       .addOnCompleteListener{
            Toast.makeText(context, "data stored", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener{
                Toast.makeText(context, "error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}