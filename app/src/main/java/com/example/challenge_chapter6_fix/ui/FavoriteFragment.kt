package com.example.challenge_chapter6_fix.ui

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.challenge_chapter6_fix.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    lateinit var  sharedPreferences : SharedPreferences
    private lateinit var builder : AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }
    companion object{
        const val USERNAME = "username"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        builder = AlertDialog.Builder(context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}