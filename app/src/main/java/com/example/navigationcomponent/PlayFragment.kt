package com.example.navigationcomponent

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.navigationcomponent.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {

    private lateinit var selectPlay: Spinner
    private lateinit var onItemSelectedListener: AdapterView.OnItemSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onItemSelectedListener = context as AdapterView.OnItemSelectedListener
        } catch (e: Exception) {
            Log.e("error", "err $e")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayBinding.inflate(layoutInflater, container, false)

        binding.buttonNext.setOnClickListener {
            navigateResult()
        }

        selectPlay = binding.spinner
        setupSelectPlayerSpinner()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val selectPlay = arguments?.getString("selectPlay")
        Log.d("ver","onResume ${selectPlay}")

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
    }

    private fun setupSelectPlayerSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.available_plays_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectPlay.adapter = adapter
        selectPlay.onItemSelectedListener = onItemSelectedListener
    }

    private fun navigateResult(){
        val argsSelectPlay = arguments?.getString("selectPlay")

        argsSelectPlay?.let {
            val action = PlayFragmentDirections.actionPlayFragmentToResultFragment(argsSelectPlay)
            findNavController().navigate(action)
        }


    }
}