package com.example.project2.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.HomeFragmentDirections
import com.example.project2.databinding.RvContactsItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class RvContactsAdapter(private val contactList: ArrayList<Contacts> ):RecyclerView.Adapter<RvContactsAdapter.ViewHolder>() {
    class ViewHolder(val binding : RvContactsItemBinding ):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvContactsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.apply {
            binding.apply {
                tvNameItem.text = currentItem.name
                tvIdItem.text = currentItem.id
                rvContainer.setOnClickListener{
                    val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(
                        currentItem.id.toString(),
                        currentItem.name.toString()
                    )
                    findNavController(holder.itemView).navigate(action)
                }

                rvContainer.setOnLongClickListener {
                    MaterialAlertDialogBuilder(holder.itemView.context)
                        .setTitle("Delete item")
                        .setMessage("Delete this Item?")
                        .setPositiveButton("Yes"){
                            _,_->
                            val firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
                            firebaseRef.child(currentItem.id.toString()).removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(holder.itemView.context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener{error ->
                                    Toast.makeText(holder.itemView.context, "error ${error.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                        .setNegativeButton("No"){
                                _,_->
                            Toast.makeText(holder.itemView.context, "canceled", Toast.LENGTH_SHORT).show()
                        }
                        .show()
                    return@setOnLongClickListener true
                }
            }
        }
    }
}