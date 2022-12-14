package com.binar.challenge_chapter6_fix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challenge_chapter6_fix.R
import com.binar.challenge_chapter6_fix.adapter.FavoriteMovieAdapter
import com.binar.challenge_chapter6_fix.databinding.FragmentFavoriteBinding
import com.binar.challenge_chapter6_fix.viewModel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]


        viewModel.getAllFavoriteMovie()
        viewModel.list_favorite.observe(viewLifecycleOwner){
            if(it == null){
                Toast.makeText(requireContext(), "Belum ada FILM Favorit", Toast.LENGTH_SHORT).show()
            }
            else {
//                adapter.setData(it.items as List<Item>)
                binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
                binding.rvPost.setHasFixedSize(false)
                binding.rvPost.adapter = FavoriteMovieAdapter(it)
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}