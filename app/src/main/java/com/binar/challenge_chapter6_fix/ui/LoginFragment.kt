package com.binar.challenge_chapter6_fix.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.challenge_chapter6_fix.R
import com.binar.challenge_chapter6_fix.ViewModelFactory
import com.binar.challenge_chapter6_fix.data.DataUserManager
import com.binar.challenge_chapter6_fix.databinding.FragmentLoginBinding
import com.binar.challenge_chapter6_fix.viewModel.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekLogin()
        var username = ""
        var password = ""

        binding.login.setOnClickListener{
            viewModel.getDataUsername().observe(viewLifecycleOwner){
                username = it.toString()
            }
            viewModel.getDataPassword().observe(viewLifecycleOwner){
                password = it.toString()
            }
            val user = binding.username.text.toString()
            val pass = binding.password.text.toString()

            if(username == user && password == pass){
                viewModel.setIsLogin(true)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "The username or password is incorrect!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun cekLogin() {
        viewModel.getIsLogin().observe(viewLifecycleOwner){
            Handler(Looper.myLooper()!!).postDelayed({
                if(it == true)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            },1000)
        }
    }
}