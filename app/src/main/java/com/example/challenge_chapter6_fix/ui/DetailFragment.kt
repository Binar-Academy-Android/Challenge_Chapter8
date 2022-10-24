package com.example.challenge_chapter6_fix.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getData()
//        setFavoriteListener()
//        checkFavoriteMovie()

        binding.btnBack.setOnClickListener(){
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

    }

//    private fun checkFavoriteMovie() {
//        binding.apply {
//            var getId = arguments?.getInt("ID")
//            viewModel.favMovie.observe(viewLifecycleOwner) {
//                if (it.id_fav == getId){
//                    binding.btnFavorit.visibility = View.GONE
//                    binding.btnFavoritCek.visibility = View.VISIBLE
//                }
//            }
//            viewModel.deleteFavMovie.observe(viewLifecycleOwner){
//                if (it.id_fav == getId){
//                    binding.btnFavorit.visibility = View.VISIBLE
//                    binding.btnFavoritCek.visibility = View.GONE
//                }
//            }
//        }
//    }

//    private fun setFavoriteListener() {
//        binding.apply {
//            binding.btnFavoritCek.setOnClickListener(){
//                removeFavorite()
//            }
//            binding.btnFavorit.setOnClickListener(){
//                addFavorite()
//            }
//
//        }
//    }

    private fun getData(){

        var getoriginalTitle = arguments?.getString("originalTitle")
        var gettitle = "Title : " + arguments?.getString("title")
        var getposterPath = arguments?.getString("posterPath")
        var getrelease = "Release : " + arguments?.getString("releaseDate")
        var getpopularity = "Popularity : " + arguments?.getDouble("popularity")
        var getlanguage = "Language : " + arguments?.getString("language")
        var getoverview = arguments?.getString("overview")

        binding.ivPoster.load("https://www.themoviedb.org/t/p/w220_and_h330_face/" + getposterPath){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_menu_24)
        }

        binding.originalTitle.setText(getoriginalTitle)
        binding.title.setText(gettitle)
        binding.releaseDate.setText(getrelease)
        binding.popularity.setText(getpopularity)
        binding.language.setText(getlanguage)
        binding.overview.setText(getoverview)

        var getId = arguments?.getInt("ID")
//        if (getId != null) {
//            data = FavoriteData(getId.toInt(), gettitle, getoriginalTitle.toString(), getoverview.toString())
//        }
    }

//    private fun removeFavorite() {
//        viewModel.deleteFavorite(data)
//        viewModel.deleteFavMovie.observe(viewLifecycleOwner) {
//            if (it != null) {
//                Toast.makeText(requireContext(), "Sukses menghapus favorit", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(requireContext(), "Failed menghapus favorit", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//        binding.btnFavorit.visibility = View.VISIBLE
////        Toast.makeText(context, "Film Favorit terhapus", Toast.LENGTH_SHORT).show()
//    }

//    private fun addFavorite() {
//        viewModel.addFavorite(data)
//        viewModel.favMovie.observe(viewLifecycleOwner) {
//            if (it != null) {
//                Toast.makeText(requireContext(), "Sukses tambah favorit", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(requireContext(), "Failed menambah favorit", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//        binding.btnFavoritCek.visibility = View.VISIBLE
////        Toast.makeText(context, "Film Favorit tersimpan", Toast.LENGTH_SHORT).show()
//    }

}
