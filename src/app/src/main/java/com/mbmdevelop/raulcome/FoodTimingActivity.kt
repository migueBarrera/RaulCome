package com.mbmdevelop.raulcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbmdevelop.raulcome.databinding.ActivityFoodTimingBinding
import com.mbmdevelop.raulcome.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodTimingActivity : AppCompatActivity() {

    private val viewModel: FoodTimingViewModel by viewModels()
    private lateinit var adapter : FoodTimingAdapter
    private lateinit var binding: ActivityFoodTimingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodTimingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun setupList(items: List<FoodTiming>){
        adapter = FoodTimingAdapter(items)
        binding.listFeedInfo.setHasFixedSize(true)
        binding.listFeedInfo.layoutManager = LinearLayoutManager(this)
        binding.listFeedInfo.adapter = adapter
        binding.listFeedInfo.scrollToPosition(items.size - 1);
    }

    private fun setupObservers() {
        viewModel.listFeedInfoObserver.observe(this){
            setupList(it)
        }
    }
}