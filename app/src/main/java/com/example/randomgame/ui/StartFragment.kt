package com.example.randomgame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomgame.R

import com.example.randomgame.databinding.StartFragmentBinding

/**
 * A Fragment representing the starting screen of the app.
 * It includes a "Почати" button that, when clicked, navigates to the GameFragment.
 */
class StartFragment : Fragment() {
    private var _binding: StartFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StartFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}