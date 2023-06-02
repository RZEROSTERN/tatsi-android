package mx.dev1.deadpool.presentation.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.dev1.deadpool.R
import mx.dev1.deadpool.databinding.FragmentRegisterBinding
import mx.dev1.deadpool.domain.models.Response

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)

        initView()
        initObservers()

        return binding.root
    }

    private fun initView() {
        binding.tatsiBtnRegisterSubmit.setOnClickListener {
            if(!validateRegisterForm()) {
                viewModel.signUp(
                    binding.tatsiTxtEmailRegister.text.toString(),
                    binding.tatsiTxtPasswordRegister.text.toString()
                )
            }
        }

        binding.tatsiBtnLoginNavigate.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun initObservers() {
        viewModel.signUpResponse.observe(viewLifecycleOwner) {
            if(it is Response.Loading) {
                Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                binding.tatsiBtnRegisterSubmit.visibility = View.INVISIBLE
            } else if(it is Response.Success) {
                Toast.makeText(requireContext(), "Success !!!", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.e.message, Toast.LENGTH_LONG).show()
            binding.tatsiBtnRegisterSubmit.visibility = View.VISIBLE
        }
    }

    private fun validateRegisterForm(): Boolean {
        var hasErrors = false

        if(binding.tatsiTxtFirstNameRegister.text.toString().isBlank() ||
            binding.tatsiTxtFirstNameRegister.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlFirstNameRegister.error =
                resources.getString(R.string.tatsi_first_name_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlFirstNameRegister.error = null
        }

        if(binding.tatsiTxtLastNameRegister.text.toString().isBlank() ||
            binding.tatsiTxtLastNameRegister.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlLastNameRegister.error =
                resources.getString(R.string.tatsi_first_last_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlLastNameRegister.error = null
        }

        if(binding.tatsiTxtPhoneRegister.text.toString().isBlank() ||
            binding.tatsiTxtPhoneRegister.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlPhoneRegister.error =
                resources.getString(R.string.tatsi_phone_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlEmailRegister.error = null
        }

        if(binding.tatsiTxtEmailRegister.text.toString().isBlank() ||
            binding.tatsiTxtEmailRegister.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlEmailRegister.error =
                resources.getString(R.string.tatsi_email_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlEmailRegister.error = null
        }

        if(binding.tatsiTxtPasswordRegister.text.toString().isBlank() ||
            binding.tatsiTxtPasswordRegister.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlPasswordRegister.error =
                resources.getString(R.string.tatsi_password_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlPasswordRegister.error = null
        }

        if(binding.tatsiTxtPasswordRegister.text.toString().length < 6) {
            binding.tatsiTxtlPasswordRegister.error =
                resources.getString(R.string.tatsi_password_minlength_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlPasswordRegister.error = null
        }

        if(binding.tatsiTxtPasswordConfirmRegister.text.toString().isBlank() ||
            binding.tatsiTxtPasswordConfirmRegister.text.toString().isNullOrEmpty()) {
            binding.tatsiTxtlPasswordConfirmRegister.error =
                resources.getString(R.string.tatsi_password_empty_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlEmailRegister.error = null
        }

        if(binding.tatsiTxtPasswordConfirmRegister.text.toString().length < 6) {
            binding.tatsiTxtlPasswordConfirmRegister.error =
                resources.getString(R.string.tatsi_password_minlength_error)
            hasErrors = true
        } else {
            binding.tatsiTxtlPasswordRegister.error = null
        }

        if(binding.tatsiTxtPasswordRegister.text.toString() !=
            binding.tatsiTxtPasswordConfirmRegister.text.toString()) {
            binding.tatsiTxtlPasswordConfirmRegister.error =
                resources.getString(R.string.tatsi_password_no_match_error)
        } else {
            binding.tatsiTxtlPasswordRegister.error = null
        }

        return hasErrors
    }
}