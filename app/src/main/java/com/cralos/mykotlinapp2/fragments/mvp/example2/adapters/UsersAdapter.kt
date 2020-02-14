package com.cralos.mykotlinapp2.fragments.mvp.example2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cralos.mykotlinapp2.R
import com.cralos.mykotlinapp2.databinding.ItemUserBinding
import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User

class UsersAdapter(var users: List<User>, var context: Context) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(context),
            R.layout.item_user,
            parent,
            false
        )
        var viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingItem.user = users.get(position)
    }

    class ViewHolder(itemView: ItemUserBinding) : RecyclerView.ViewHolder(itemView.root) {
        var bindingItem = itemView
    }

}