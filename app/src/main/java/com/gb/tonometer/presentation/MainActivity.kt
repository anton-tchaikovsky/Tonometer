package com.gb.tonometer.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gb.tonometer.TonometerApp
import com.gb.tonometer.databinding.ActivityMainBinding
import com.gb.tonometer.presentation.recycle_view.ItemTouchHelperCallback
import com.gb.tonometer.presentation.recycle_view.TonometerAdapter
import com.gb.tonometer.presentation.view_entity.ViewTonometerData
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val tonometerAdapter: TonometerAdapter =
        TonometerAdapter { viewModel.deleteTonometerData(it) }

    @Inject
    lateinit var viewModelFactory: TonometerViewModelFactory

    private val viewModel: TonometerViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TonometerApp.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        viewModel.getTonometerLiveData().observe(this) {
            showTonometerData(it)
        }
        if (savedInstanceState == null)
            viewModel.getTonometerData()
    }

    private fun initView() {
        initFab()
        initRecyclerView()
    }

    private fun initFab() {
        binding.floatingActionButton.setOnClickListener {
            AddTonometerMeasurementDialogFragment.newInstance().show(
                supportFragmentManager,
                AddTonometerMeasurementDialogFragment.TAG_DIALOG_FRAGMENT
            )
        }
        setAddTonometerMeasurementDialogFragment()
    }

    private fun setAddTonometerMeasurementDialogFragment() {
        supportFragmentManager.setFragmentResultListener(
            AddTonometerMeasurementDialogFragment.KEY_DIALOG_FRAGMENT, this
        ) { requestKey, result ->
            if (requestKey == AddTonometerMeasurementDialogFragment.KEY_DIALOG_FRAGMENT)
                result.getStringArrayList(AddTonometerMeasurementDialogFragment.KEY_MEASUREMENT)
                    ?.let {
                        viewModel.insertTonometerData(it.toList())
                    }
        }
    }

    private fun initRecyclerView() {
        binding.tonometerRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = tonometerAdapter
            ItemTouchHelper(ItemTouchHelperCallback(tonometerAdapter))
                .attachToRecyclerView(this@apply)
        }
    }

    private fun showTonometerData(tonometerData: List<ViewTonometerData>) {
        tonometerAdapter.setTonometerData(tonometerData)
        binding.tonometerRecyclerView.scrollToPosition(tonometerAdapter.itemCount - 1)
    }

}