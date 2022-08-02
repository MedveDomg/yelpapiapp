package com.medvedomg.yelpapiapp.presentation.businesslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.medvedomg.yelpapiapp.databinding.BusinessViewholderBinding

class BusinessListAdapter : RecyclerView.Adapter<BusinessListAdapter.BusinessViewHolder>() {

    private var itemsList: MutableList<BusinessModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val binding = BusinessViewholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BusinessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size

    fun setData(list: List<BusinessModel>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class BusinessViewHolder(val binding: BusinessViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(businessModel: BusinessModel) {
            with(binding) {
                iv.load(businessModel.imageUrl)
                tvName.text = businessModel.name
                if (businessModel.location.isNotBlank()) {
                    tvLocation.text = businessModel.location
                    tvLocation.visibility = View.VISIBLE
                } else {
                    tvLocation.visibility = View.GONE
                }
                tvIsClosed.text = "Is closed: ${businessModel.isClosed}"
            }
        }
    }
}