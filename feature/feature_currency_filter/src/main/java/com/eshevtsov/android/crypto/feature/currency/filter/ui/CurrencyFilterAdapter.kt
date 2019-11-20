package com.eshevtsov.android.crypto.feature.currency.filter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eshevtsov.android.crypto.data.dto.CoinIdDto
import com.eshevtsov.android.crypto.feature.currency.filter.databinding.LayoutCurrencyFilterItemBinding

class CurrencyFilterAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val items = mutableListOf<CoinIdDto>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutCurrencyFilterItemBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.model = item
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<CoinIdDto>) {
        this.items.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}

data class CurrencyViewHolder(
    val binding: LayoutCurrencyFilterItemBinding
) : RecyclerView.ViewHolder(binding.root)