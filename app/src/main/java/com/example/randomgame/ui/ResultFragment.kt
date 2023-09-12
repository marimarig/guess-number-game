package com.example.randomgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.randomgame.R
import com.example.randomgame.databinding.ResultFragmentBinding
import com.example.randomgame.viewmodel.GameViewModel

class ResultFragment : Fragment() {
    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by  activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.apply {
           lifecycleOwner =  viewLifecycleOwner
           viewModelResult = viewModel
           resultFragment = this@ResultFragment
       }

        viewModel.resultMessage.observe(viewLifecycleOwner, Observer { message ->
            // Update the text on the screen depending on the message in the viewModel
            binding.textView.text = message
        })
    }

    // Function to restart the game when the user taps a button.
    fun restartGame() {
        viewModel.startNewGame()
        findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
