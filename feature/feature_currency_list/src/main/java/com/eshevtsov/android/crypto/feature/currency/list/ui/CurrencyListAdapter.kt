package com.eshevtsov.android.crypto.feature.currency.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eshevtsov.android.crypto.feature.currency.list.databinding.LayoutCurrencyItemBinding
import com.eshevtsov.android.crypto.feature.currency.list.domain.CoinModel

class CurrencyListAdapter(
    private var onItemClick: ((CoinModel) -> Unit)?
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    private var items = listOf<CoinModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutCurrencyItemBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = items[position]
        holder.run {
            binding.model = item
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<CoinModel>) {
        val callback = DiffCallback(this.items, items)
        val result = DiffUtil.calculateDiff(callback)
        this.items = items
        result.dispatchUpdatesTo(this)
    }

    fun release() {
        onItemClick = null
    }
}

data class CurrencyViewHolder(
    val binding: LayoutCurrencyItemBinding
) : RecyclerView.ViewHolder(binding.root)

class DiffCallback(
    private val oldItems: List<CoinModel>,
    private val newItems: List<CoinModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}