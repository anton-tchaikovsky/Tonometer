package com.gb.tonometer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.gb.tonometer.databinding.FragmentAddTonometerMeasurementBinding

class AddTonometerMeasurementDialogFragment: DialogFragment() {

    private var _binding: FragmentAddTonometerMeasurementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTonometerMeasurementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSaveButton()
        initCancelButton()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
    }

    private fun initSaveButton(){
        binding.saveButton.setOnClickListener {
            requireActivity().supportFragmentManager.setFragmentResult(
                KEY_DIALOG_FRAGMENT,
                bundleOf(KEY_MEASUREMENT to extractMeasurement())
            )
            dismissAllowingStateLoss()
        }
    }

    private fun extractMeasurement(): ArrayList<String>{
        val systolic = binding.systolicPressureEditText.text.toString()
        val diastolic = binding.diastolicPressureEditText.text.toString()
        val pulse = binding.pulseEditText.text.toString()
        return ArrayList<String>().apply {
            add(systolic)
            add (diastolic)
            add(pulse)
        }
    }

    private fun initCancelButton(){
        binding.cancelButton.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): AddTonometerMeasurementDialogFragment =
            AddTonometerMeasurementDialogFragment()

        const val KEY_MEASUREMENT = "KeyMeasurement"

        const val KEY_DIALOG_FRAGMENT = "KeyDialogFragment"

        const val TAG_DIALOG_FRAGMENT = "AddTonometerMeasurementDialogFragment"
    }
}