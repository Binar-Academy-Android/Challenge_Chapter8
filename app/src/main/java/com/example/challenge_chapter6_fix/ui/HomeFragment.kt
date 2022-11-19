package com.example.challenge_chapter6_fix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_chapter6_fix.MainViewModelFactory
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.ViewModelFactory
import com.example.challenge_chapter6_fix.adapter.MovieAdapter
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.databinding.FragmentHomeBinding
import com.example.challenge_chapter6_fix.model.Item
import com.example.challenge_chapter6_fix.service.ApiClient
import com.example.challenge_chapter6_fix.service.ApiHelper
import com.example.challenge_chapter6_fix.viewModel.MovieViewModel
import com.example.challenge_chapter6_fix.viewModel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), MovieAdapter.ListMovieInterface {

    private lateinit var userViewModel: UserViewModel
    private lateinit var pref: DataUserManager

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieViewModel : MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pref = DataUserManager(requireContext())
        userViewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        movieViewModel = ViewModelProvider(
            this, MainViewModelFactory(ApiHelper(ApiClient.instance))
        )[MovieViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter: MovieAdapter by lazy {
            MovieAdapter {

            }
        }
        userViewModel.getDataUsername().observe(viewLifecycleOwner) {
            binding.tvUser.text = it.toString()
        }

        @Suppress("UNCHECKED_CAST")
        binding.apply {
            movieViewModel.showItemListData()
            movieViewModel.getLiveDataMovie().observe(viewLifecycleOwner){
                if ( it != null){
                    adapter.setData(it.items as List<Item>)
                }else{
                    Snackbar.make(binding.root, "Data Gagal Dimuat", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(
                            ContextCompat.getColor(requireContext(),
                                R.color.colorPrimary
                            ))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                        .show()
                }
            }
            rvPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvPost.adapter = adapter

        }

        binding.btnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.btnFavorit.setOnClickListener {
            userViewModel.setIsLogin(false)
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onItemClick(MovieDetail: Item) {
        TODO("Not yet implemented")
    }

}