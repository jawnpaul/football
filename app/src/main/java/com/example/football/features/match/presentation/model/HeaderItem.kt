package com.example.football.features.match.presentation.model

import com.example.football.R
import com.example.football.databinding.SingleHeaderItemBinding
import com.xwray.groupie.databinding.BindableItem

class HeaderItem(private val time: String) : BindableItem<SingleHeaderItemBinding>() {
    override fun bind(binding: SingleHeaderItemBinding, p1: Int) {
        binding.singleActionTimeTextView.text = time
        binding.executePendingBindings()
    }

    override fun getLayout(): Int = R.layout.single_header_item
}
