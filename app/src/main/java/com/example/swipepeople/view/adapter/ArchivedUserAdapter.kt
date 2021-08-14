package com.example.swipepeople.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swipepeople.R
import com.example.swipepeople.data.model.ArchivedUser
import com.example.swipepeople.databinding.ItemArchivedUserBinding

class ArchivedUserAdapter(private val archiveUsers: List<ArchivedUser>) :
    RecyclerView.Adapter<ArchivedUserAdapter.ArchiveViewHolder>() {

    inner class ArchiveViewHolder(val binding: ItemArchivedUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveViewHolder {
        return ArchiveViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_archived_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        holder.binding.user = archiveUsers[position]
    }

    override fun getItemCount() = archiveUsers.size
}