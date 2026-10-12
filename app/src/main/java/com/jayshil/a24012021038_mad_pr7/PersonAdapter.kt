package com.jayshil.a24012021038_mad_pr7

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jayshil.a24012021038_mad_pr7.databinding.ActivityPersonBinding

class PersonAdapter(
    private val context: Context,
    private val personList: ArrayList<Person>,
    private val databaseHelper: DatabaseHelper
) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(
        val binding: ActivityPersonBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ActivityPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int = personList.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]

        holder.binding.txtName.text = person.name
        holder.binding.txtPhone.text = person.phoneNo
        holder.binding.txtEmail.text = person.emailId
        holder.binding.txtAddress.text = person.address

        holder.binding.btnDelete.setOnClickListener {
            val currentPos = holder.bindingAdapterPosition
            if (currentPos != RecyclerView.NO_POSITION) {
                databaseHelper.deletePerson(person.id)
                personList.removeAt(currentPos)
                notifyItemRemoved(currentPos)
                notifyItemRangeChanged(currentPos, personList.size)

                Toast.makeText(context, "Person deleted", Toast.LENGTH_SHORT).show()
            }
        }

        val openMapAction = {
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra("Object", person)
            context.startActivity(intent)
        }

        holder.binding.btnMap.setOnClickListener {
            openMapAction()
        }

        holder.itemView.setOnClickListener {
            openMapAction()
        }
    }
}