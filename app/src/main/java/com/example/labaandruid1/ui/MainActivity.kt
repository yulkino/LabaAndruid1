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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBinding()
        setAdapter()
        load()
    }

    private fun load(){
        MainScope().launch {
            binding.loading.isVisible = true
            val tariffsCallback = object: Callback<List<Tariff>> {
                override fun onResponse(call: Call<List<Tariff>>, response: Response<List<Tariff>>) {
                    val tariffs = response.body() ?: onFailure(call, Exception("body is null"))
                    val items = (tariffs as List<Tariff>).map(::mapTariffToItem)
                    setTariffs(items)
                    binding.loading.isVisible = false
                }

                override fun onFailure(call: Call<List<Tariff>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
                    binding.loading.isVisible = false
                }
            }

            val balanceCallback = object: Callback<List<Balance>> {
                override fun onResponse(
                    call: Call<List<Balance>>,
                    response: Response<List<Balance>>,
                ) {
                    val balance = response.body()?.get(0) ?: onFailure(call, Exception())
                    val casted = balance as Balance
                    with(binding) {
                        pay.text = casted.balance.toString()
                        nextPay.text = getString(R.string.nextPay).format(casted.nextPay)
                        ls.text = getString(R.string.ls).format(casted.accNum)
                        loading.isVisible = false
                    }
                }

                override fun onFailure(call: Call<List<Balance>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
                    binding.loading.isVisible = false
                }
            }

            val userCallback = object: Callback<List<UserInfo>> {
                override fun onResponse(call: Call<List<UserInfo>>, response: Response<List<UserInfo>>) {
                    val user = response.body()?.get(0) ?: onFailure(call, Exception())
                    val casted = (user as UserInfo)
                    with(binding) {
                        fio.text = "${casted.firstName} ${casted.lastName}"
                        address.text = user.address
                        loading.isVisible = false
                    }
                }

                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
                    binding.loading.isVisible = false
                }
            }

            api.getTariffs().enqueue(tariffsCallback)
            api.getBalance().enqueue(balanceCallback)
            api.getUserInfo().enqueue(userCallback)
        }
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

