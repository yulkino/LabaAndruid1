package com.example.labaandruid1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.labaandruid1.databinding.ActivityMainBinding
import com.example.labaandruid1.databinding.TarifRvBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = Adapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )

        val list = listOf<Item>(
            Item(
                "Тариф «Улыбка (бесплатный)»",
                "Скорость до 100 Мбит/с",
                0
            ),
            Item(
                "Тариф «Бутылка»",
                "Скорость до 150 Мбит/с",
                50
            ),
            Item(
                "Тариф «День Сюрка»",
                "Скорость до 500 Мбит/с",
                500
            ),
        )
        adapter.submitList(list)
    }
}

class Adapter: ListAdapter<Item, VH>(
    object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(TarifRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

}

data class Item(
    val title: String,
    val subtitle: String,
    val price: Int,
)

class VH(private val binding: TarifRvBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.tarifName.text = item.title
        binding.speed.text = item.subtitle
        binding.pay.text = "${item.price} ₽"
    }
}
