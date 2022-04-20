package com.example.labaandruid1.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labaandruid1.R
import com.example.labaandruid1.databinding.ActivityMainBinding
import com.example.labaandruid1.network.models.Balance
import com.example.labaandruid1.network.models.Tariff
import com.example.labaandruid1.network.models.UserInfo
import com.example.labaandruid1.network.retrofit.ApiProvider
import com.example.labaandruid1.network.retrofit.RetrofitClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val api = ApiProvider(RetrofitClient()).getApi()

    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding

    private var isUserLoaded = false
        private set(value) {
            field = value
            checkLoading()
        }
    private var isTariffLoaded = false
        private set(value) {
            field = value
            checkLoading()
        }
    private var isBalanceLoaded = false
        private set(value) {
            field = value
            checkLoading()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBinding()
        setAdapter()
        load()
    }

    private fun load(){
        MainScope().launch { loadUserInfo() }
        MainScope().launch { loadTariff() }
        MainScope().launch { loadBalance() }
    }

    private suspend fun loadBalance() {
        isBalanceLoaded = false
        val balance = api.getBalance()[0]
        with(binding){
            ls.text = "ЛС: ${balance.accNum.toString()}"
            pay.text = balance.balance.toString()
            nextPay.text = "К оплате за сентябрь ${balance.nextPay}"
            isBalanceLoaded = true
        }
    }

    private suspend fun loadTariff() {
        isTariffLoaded = false
        val tariff = api.getTariffs()
        setTariffs(tariff.map(::mapTariffToItem))
        isTariffLoaded = true
    }

    private suspend fun loadUserInfo(){
        isUserLoaded = false
        val user = api.getUserInfo()[0]
        with(binding){
            fio.text = "${user.firstName} ${user.lastName}"
            address.text = user.address
            isUserLoaded = true
        }
    }

    private fun checkLoading() {
        val loadingDone = isUserLoaded && isTariffLoaded && isBalanceLoaded
        binding.loading.isVisible = !loadingDone
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

