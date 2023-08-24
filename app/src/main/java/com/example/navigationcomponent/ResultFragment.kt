package com.example.navigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.navigationcomponent.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var engine: JokenpoEngine
    lateinit var resultText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentResultBinding.inflate(layoutInflater, container, false)

        binding.buttonNext3.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_playFragment)
        }

        engine = JokenpoEngine(resources.getStringArray(R.array.available_plays_array))

        val selectPlay = arguments?.getString("selectPlay")
        resultText = binding.textResult

        selectPlay?.let {
            updateResultText(selectPlay)
        }

        binding.textViewUser.text = selectPlay
        binding.textViewAi.text = engine.aiPlay
        return binding.root
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController())
    }

    private fun updateResultText(selectPlay: String){
        val result = engine.calculateResult(selectPlay)

        resultText.text = when(result){
            Result.WIN -> "Você ganhou :)"
            Result.LOSS -> "Você perdeu :("
            else -> "Empate!"
        }
    }
}