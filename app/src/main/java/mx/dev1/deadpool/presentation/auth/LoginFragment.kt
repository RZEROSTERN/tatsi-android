package mx.dev1.deadpool.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.dev1.deadpool.R
import mx.dev1.deadpool.databinding.FragmentLoginBinding
import mx.dev1.deadpool.domain.models.Response.Success
import mx.dev1.deadpool.domain.models.Response.Loading
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        initView()
        initObservers()

        return binding.root
    }

    private fun initView() {
        binding.tatsiBtnLogin.setOnClickListener {
            if(!validateLoginForm()) {
                viewModel.signIn(
                    binding.tatsiTxtEmailLogin.text.toString(),
                    binding.tatsiTxtPasswordLogin.text.toString()
                )
            }
        }

        binding.tatsiBtnRegister.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun initObservers() {
        viewModel.signInResponse.observe(viewLifecycleOwner) {
            if(it is Loading) {
                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                binding.tatsiBtnLogin.visibility = View.INVISIBLE
            } else if(it is Success) {
                Toast.makeText(requireContext(), "Success !!!", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.e.message, Toast.LENGTH_LONG).show()
            binding.tatsiBtnLogin.visibility = View.VISIBLE
        }
    }

    private fun validateLoginForm(): Boolean {
        var hasErrors = false

        if(binding.tatsiTxtEmailLogin.text.toString().isBlank() ||
                binding.tatsiTxtEmailLogin.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlEmailLogin.error =
                resources.getString(R.string.tatsi_email_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlEmailLogin.error = null
        }

        if(binding.tatsiTxtPasswordLogin.text.toString().isBlank() ||
                binding.tatsiTxtPasswordLogin.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlPasswordLogin.error =
                resources.getString(R.string.tatsi_password_empty_error)
            hasErrors = true
        }  else {
            binding.tatsiTxtlPasswordLogin.error = null
        }

        if(binding.tatsiTxtPasswordLogin.text.toString().length < 6) {
            binding.tatsiTxtlPasswordLogin.error =
                resources.getString(R.string.tatsi_password_minlength_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlPasswordLogin.error = null
        }

        return hasErrors
    }
}