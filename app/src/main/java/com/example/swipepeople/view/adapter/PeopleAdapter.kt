package com.example.swipepeople.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swipepeople.R
import com.example.swipepeople.data.model.Result
import com.example.swipepeople.databinding.ItemCardViewBinding
import com.example.swipepeople.view.ItemClickListener

class PeopleAdapter(private val people: List<Result>) :
    RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>(), ItemClickListener {

    inner class PeopleViewHolder(val binding: ItemCardViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_card_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.binding.user = people[position].user
        holder.binding.listener = this
    }

    override fun getItemCount() = people.size

    override fun onItemClicked(v: View, text: String) {
        Toast.makeText(v.context, text, Toast.LENGTH_SHORT).show()
    }
}