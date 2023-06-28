package com.mbmdevelop.raulcome

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbmdevelop.raulcome.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter :FeedInfoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupObservers()

        binding.fab.setOnClickListener {
            if (viewModel.hasAPendingFeedInfoForClose()){
                askByBoobs()
            }else{
                askWhenStarted()
            }
        }

        binding.fabFoodTiming.setOnClickListener {
            goToFoodTiming()
        }
    }

    private fun goToFoodTiming() {
        val i = Intent(this,FoodTimingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(i)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun askWhenStarted() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.when_you_started)
            .setNeutralButton(R.string.ten_minutes_ago) { _, _ ->
                val time = getDateWithMinutesSUM(-10)
                viewModel.openFeedInfo(time)
            }
            .setPositiveButton(R.string.now
            ) { _, _ ->
                viewModel.openFeedInfo(Date())
            }
            .setNegativeButton(R.string.five_minutes_ago
            ) { _, _ ->
                val time = getDateWithMinutesSUM(-5)
                viewModel.openFeedInfo(time)
            }
        builder.create().show()
    }

    private fun askByBoobs() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.what_boob_you_use)
            .setNeutralButton(R.string.bothboobs) { _, _ ->
                viewModel.closeLastFeedInfo(Date(), 3)
            }
            .setPositiveButton(R.string.right
            ) { _, _ ->
                viewModel.closeLastFeedInfo(Date(), 2)
            }
            .setNegativeButton(R.string.left
            ) { _, _ ->
                viewModel.closeLastFeedInfo(Date(), 1)
            }
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_alarm -> viewModel.openAlarmApp()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupList(items: List<FeedInfo>){
        adapter = FeedInfoAdapter(items.toMutableList())
        binding.listFeedInfo.setHasFixedSize(true)
        binding.listFeedInfo.layoutManager = LinearLayoutManager(this)
        binding.listFeedInfo.adapter = adapter
        binding.listFeedInfo.scrollToPosition(items.size - 1);
        binding.listFeedInfo.setNestedScrollingEnabled(false);
    }

    private fun setupObservers() {
        viewModel.listFeedInfoObserver.observe(this){
            setupList(it)
        }
    }
}