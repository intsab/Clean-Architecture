package com.intsab.dls

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.intsab.dls.databinding.CurrencyItemBinding

/**
 * Created by intsabhaider
 * on 26,April,2023
 */
class CurrencyItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: CurrencyItemBinding by lazy {
        CurrencyItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.dls_currency_item_attr)
        val currency = ta.getString(R.styleable.dls_currency_item_attr_currency) ?: ""
        val country = ta.getString(R.styleable.dls_currency_item_attr_country) ?: ""
        val amount = ta.getString(R.styleable.dls_currency_item_attr_amount) ?: ""
        val isGridItem = ta.getBoolean(R.styleable.dls_currency_item_attr_isGridItem, false)


        ta.recycle()

        currency(currency).country(country).amount(amount).isGridItem(isGridItem)
    }

    fun currency(title: CharSequence): CurrencyItemView {
        binding.currency.text = title
        return this
    }

    fun country(title: CharSequence): CurrencyItemView {
        binding.country.text = title
        return this
    }

    fun amount(title: CharSequence): CurrencyItemView {
        binding.amount.text = title
        return this
    }

    fun isGridItem(isGrid: Boolean): CurrencyItemView {
        if (!isGrid) {
            binding.country.visibility = View.GONE
            binding.amount.visibility = View.GONE
            binding.currency.visibility = View.VISIBLE
        } else {
            binding.country.visibility = View.VISIBLE
            binding.amount.visibility = View.VISIBLE
            binding.currency.visibility = View.GONE
        }
        return this
    }
}