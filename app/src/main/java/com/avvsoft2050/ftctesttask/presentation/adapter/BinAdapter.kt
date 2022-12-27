package com.avvsoft2050.ftctesttask.presentation.adapter

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.ftctesttask.data.model.Card
import com.avvsoft2050.ftctesttask.databinding.ItemBinBinding

class BinAdapter(
    private val onClickAction: (Card) -> Unit
) : ListAdapter<Card, BinAdapter.BinViewHolder>(CardsDiffUtil()) {

    inner class BinViewHolder(val binding: ItemBinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.textViewBinItem.text = card.bin
        }
    }

    class CardsDiffUtil : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.bin == newItem.bin
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinBinding.inflate(inflater)
        return BinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClickAction(item)
        }
    }
}