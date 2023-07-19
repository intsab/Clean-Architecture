package com.intsab.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.intsab.core.coredata.datamodels.CurrencyItemB
import com.intsab.core.coredata.datamodels.RateB
import com.intsab.core.room.CurrencyDatabase
import com.intsab.currencyconverter.adapters.CurrencySpinnerAdapter
import com.intsab.currencyconverter.adapters.RatesGridAdapter
import com.intsab.currencyconverter.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    var currentSelectedCurrency = "USD"
    private val currenciesList: ArrayList<CurrencyItemB> = arrayListOf()
    private val usdRateList: ArrayList<RateB> = arrayListOf()
    private val currentSelectedRateList: ArrayList<RateB> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var rateAdapter: RatesGridAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        try {
            CurrencyDatabase.setContext(applicationContext).init().configCurrency().configLatest()
        } catch (exp: Exception) {
            exp.printStackTrace()
        }

        setContentView(binding.root)
        (application as CurrencyApp).appComponent!!.inject(this)
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        rateAdapter = RatesGridAdapter(currentSelectedRateList)

        binding.rv.adapter = rateAdapter

        getLatestRateApiCall()
        getAllCurrenciesApiCall()
        addObservers()
        setupUiAndListeners()
    }

    private fun setupUiAndListeners() {
        binding.rv.layoutManager = GridLayoutManager(this, 3)
        binding.spCurrencies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                currentSelectedCurrency = currenciesList[position].currencyName
                calculateRateAndNotify()
            }
        }

        binding.etCurrency.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                currentSelectedRateList.forEach {
                    it.qty = getQty()
                }
                rateAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun addObservers() {
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, "" + it, Toast.LENGTH_SHORT).show()
        })

        viewModel.getLatestRateLiveData.observe(this, Observer {
            usdRateList.clear()
            usdRateList.addAll(it.rates)
            calculateRateAndNotify()
        })
        viewModel.getCurrenciesLiveData.observe(this, Observer {
            currenciesList.clear()
            currenciesList.addAll(it.currencies)

            binding.spCurrencies.adapter = CurrencySpinnerAdapter(this, currenciesList)
            if (currenciesList.isNotEmpty() && currenciesList.find { it.currencyName == "USD" } != null) {
                try {
                    binding.spCurrencies.setSelection(currenciesList.indexOf(currenciesList.filter { it.currencyName == "USD" }[0]))
                } catch (exp: Exception) {
                    exp.printStackTrace()
                }
            }
        })
    }

    fun calculateRateAndNotify() {
        val list = arrayListOf<RateB>()
        currentSelectedRateList.clear()
        if (currentSelectedCurrency == "USD") {
            currentSelectedRateList.addAll(usdRateList)
        } else {
            val baseRate: Double =
                usdRateList.find { it.currency == currentSelectedCurrency }?.rate ?: 1.0
            usdRateList.forEach {
                list.add(RateB(it.currency, (it.rate / baseRate), getQty()))
            }
        }
        currentSelectedRateList.addAll(list)

        rateAdapter.notifyDataSetChanged()
    }

    private fun getLatestRateApiCall() {
        viewModel.getLatestRate("USD")
    }

    private fun getAllCurrenciesApiCall() {
        viewModel.getCurrencies()
    }

    private fun getQty(): Double {
        return try {
            binding.etCurrency.text.toString().toDouble()
        } catch (exp: Exception) {
            1.0
        }
    }

}