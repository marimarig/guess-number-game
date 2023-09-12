package com.example.randomgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.randomgame.R
import com.example.randomgame.databinding.GameFragmentBinding
import com.example.randomgame.viewmodel.GameViewModel

class GameFragment : Fragment() {
    // Obtain a reference to the shared ViewModel using activityViewModels.
    private val viewModel: GameViewModel by activityViewModels()

    // View binding for the Fragment layout.
    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    // Inflate the Fragment's layout when creating the view.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Set up data binding and lifecycle owner when the view is created.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            gameViewModel = viewModel
            gameFragment = this@GameFragment
        }
    }

    /**
     * Function to handle user input and game logic
     * when the "Next" button is pressed.
     */
    fun goToNextScreen() {
        val userGuess = binding.guessNumber.text.toString().toIntOrNull()
        if (userGuess != null) {
            viewModel.checkGuess(userGuess)
            checkGameResult()
        } else {
            showToast(getString(R.string.enter_number))
        }
    }

    // Function to check the game result and navigate to the result screen if needed.
    private fun checkGameResult() {
        val resultMessage = viewModel.resultMessage.value
        val attemptsLeft = viewModel._attemptsLeft
        val remainingAttemptsMessage = when (resultMessage) {
            getString(R.string.you_win), getString(R.string.you_lost) -> {
                navigateToResultFragment()
                return
            }
            getString(R.string.not_enough), getString(R.string.too_much) ->
                "$resultMessage ${getString(R.string.attempt)} $attemptsLeft"
            else -> ""
        }
        if (remainingAttemptsMessage.isNotBlank()) {
            showToast(remainingAttemptsMessage)
        }
    }

    // Function to navigate to the result fragment.
    private fun navigateToResultFragment() {
        findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
    }

    // Function to show a toast message.
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // Clean up the view binding when the view is destroyed.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}