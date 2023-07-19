package com.intsab.currencyconverter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intsab.core.coredata.datamodels.RateB
import com.intsab.currencyconverter.databinding.RatesItemLayoutBinding
import java.util.*

/**
 * Created by intsabhaider
 * on 01,May,2023
 */
class RatesGridAdapter(
    private val data: ArrayList<RateB>
) : RecyclerView.Adapter<RatesGridAdapter.RateViewHolder>() {

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
            RatesItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RateViewHolder(private val binding: RatesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RateB) {

            binding.ratesView.country(data.currency)
            binding.ratesView.amount(
                "" + String.format(
                    Locale("en", "US"), "%.4f", (data.rate * data.qty)
                )
            )
            binding.ratesView.setOnClickListener {
                mOnClickListener.onClick(it)
            }
        }
    }
}