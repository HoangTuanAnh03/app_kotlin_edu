package com.anhht.edu.views.profile.orderhistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anhht.edu.databinding.ActivityOrderHistoryBinding
import com.anhht.edu.repository.OrderAPIService
import com.anhht.edu.viewmodels.OrderViewModel
import com.anhht.edu.adapter.RVOrderHistoryAdapter

class OrderHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderHistoryBinding
    private lateinit var rvMain: RecyclerView
    private lateinit var orderAdapter: RVOrderHistoryAdapter
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
    }
    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        Log.e("context1", this@OrderHistoryActivity.toString())
        rvMain = binding.OrderHistoryRecycleView
        rvMain.layoutManager = LinearLayoutManager(this)
        orderViewModel = OrderViewModel(OrderAPIService())
        orderViewModel.getOrderHistory(this@OrderHistoryActivity).observe(this@OrderHistoryActivity){data->
            orderAdapter = RVOrderHistoryAdapter(this@OrderHistoryActivity, data)
            rvMain.adapter = orderAdapter
        }
        binding.orderHistoryBack.setOnClickListener{
            finish()
        }
    }
}