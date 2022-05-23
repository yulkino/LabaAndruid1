package com.example.labaandruid1.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.labaandruid1.databinding.TarifRvBinding

class VH(private val binding: TarifRvBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.tarifName.text = item.title
        binding.speed.text = item.subtitle
        binding.pay.text = "${item.price} ₽"
        binding.delete.setOnClickListener{
            item.onDelete()
        }
    }
}