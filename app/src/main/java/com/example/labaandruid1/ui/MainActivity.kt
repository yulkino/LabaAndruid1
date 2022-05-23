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
import com.example.labaandruid1.di.factory
import com.example.labaandruid1.viewModule.AbstractMainViewModule

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding

    private val viewModule by viewModels<AbstractMainViewModule>{ factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        viewModule.tariffData.observe(this){ list ->
            setTariffs(list.map {item -> mapTariffToItem(item) })
        }
        viewModule.userInfoData.observe(this){
            with(binding){
                fio.text = "${it.firstName} ${it.lastName}"
                address.text = it.address
            }
        }
        viewModule.balanceData.observe(this){
            with(binding){
                ls.text = "ЛС: ${it.accNum.toString()}"
                pay.text = it.balance.toString()
                nextPay.text = "К оплате за сентябрь ${it.nextPay}"
            }
        }
    }

    private fun mapTariffToItem(tariff: Tariff) =
        Item(
            title = tariff.title,
            subtitle = tariff.desc,
            price = tariff.cost,
            onDelete = { viewModule.delete(tariff) },
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

