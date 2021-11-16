package com.codechallenge.apilist.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.codechallenge.apilist.activity.DetailActivity
import com.codechallenge.apilist.R

class ApiListAdapter(private val mData: List<ItemsViewModel>) : RecyclerView.Adapter<ApiListAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_apilist, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mData[position]
        holder.tv_title.text = itemsViewModel.api
        holder.itemLayout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("ItemData", itemsViewModel)
            intent.putExtras(bundle)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemLayout: ConstraintLayout = itemView.findViewById(R.id.itemLayout)
        val tv_title: TextView = itemView.findViewById(R.id.tv_apititle)
    }
}