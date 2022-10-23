package com.example.challenge_chapter6_fix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.ViewModelFactory
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.databinding.FragmentUpdateBinding
import com.example.challenge_chapter6_fix.viewModel.UserViewModel

class UpdateFragment : Fragment() {
    lateinit var binding: FragmentUpdateBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pref = DataUserManager(requireContext())
        userViewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getDataUsername().observe(viewLifecycleOwner) {
            binding.edtUsername.setText(it.toString())
        }

        userViewModel.getName().observe(viewLifecycleOwner) {
            binding.edtName.setText(it.toString())
        }

        userViewModel.getEmail().observe(viewLifecycleOwner) {
            binding.edtEmail.setText(it.toString())
        }

        userViewModel.getBirthday().observe(viewLifecycleOwner) {
            binding.edtBirthday.setText(it.toString())
        }

        userViewModel.getNomor().observe(viewLifecycleOwner) {
            binding.edtNomor.setText(it.toString())
        }

        var password = ""
        userViewModel.getDataPassword().observe(viewLifecycleOwner) {
            password = it.toString()
        }

        binding.apply {
            binding.btnEdit.setOnClickListener(){
                val username = binding.edtUsername.text.toString()
                val name = binding.edtName.text.toString()
                val email = binding.edtEmail.text.toString()
                val birthday = binding.edtBirthday.text.toString()
                val nomor = binding.edtNomor.text.toString()

                userViewModel.saveUser(username, name, email, birthday, nomor, password)
                Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_profileFragment)
            }

        }
    }
}