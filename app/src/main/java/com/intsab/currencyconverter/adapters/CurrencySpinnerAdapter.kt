package com.intsab.currencyconverter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.intsab.core.coredata.datamodels.CurrencyItemB
import com.intsab.currencyconverter.R
import com.intsab.dls.CurrencyItemView

/**
 * Created by intsabhaider
 * on 01,May,2023
 */
class CurrencySpinnerAdapter(val context: Context, var dataSource: List<CurrencyItemB>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.spinner_item_layout, parent, false)
            vh = ItemHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.currencyView.currency(dataSource[position].currencyName)


        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ItemHolder(row: View?) {
        val currencyView: CurrencyItemView

        init {
            currencyView = row?.findViewById(R.id.currencyItemView) as CurrencyItemView
        }
    }

}