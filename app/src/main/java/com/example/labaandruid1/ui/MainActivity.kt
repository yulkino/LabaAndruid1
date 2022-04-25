package com.example.labaandruid1.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labaandruid1.R
import com.example.labaandruid1.databinding.ActivityMainBinding
import com.example.domain.models.Tariff
import com.example.labaandruid1.App
import com.example.labaandruid1.viewModule.AbstractMainViewModule
import com.example.labaandruid1.viewModule.ViewModelFactory
import com.example.network.retrofit.ApiProvider
import com.example.network.retrofit.RetrofitClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var factory: ViewModelFactory

    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding

    private val viewModule by viewModels<AbstractMainViewModule>{ factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inject()
        setBinding()
        setAdapter()
        subscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModule.refreshData()
    }

    private fun subscribe() {
        viewModule.isLoading.observe(this){
            binding.loading.isVisible = it
        }
        viewModule.tariff.observe(this){ list ->
            setTariffs(list.map {item -> mapTariffToItem(item) })
        }
        viewModule.userInfo.observe(this){
            with(binding){
                fio.text = "${it.firstName} ${it.lastName}"
                address.text = it.address
            }
        }
        viewModule.balance.observe(this){
            with(binding){
                ls.text = "ЛС: ${it.accNum.toString()}"
                pay.text = it.balance.toString()
                nextPay.text = "К оплате за сентябрь ${it.nextPay}"
            }
        }
    }

    private fun inject() {
        App.appComponent.inject(this)
    }

    private fun mapTariffToItem(tariff: Tariff) =
        Item(
            title = tariff.title,
            subtitle = tariff.desc,
            price = tariff.cost,
        )

    private fun setTariffs(list: List<Item>) {
        adapter.submitList(list)
    }

    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setAdapter() {
        adapter = Adapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )
    }
}

