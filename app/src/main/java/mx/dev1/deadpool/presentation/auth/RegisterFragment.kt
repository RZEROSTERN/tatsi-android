package mx.dev1.deadpool.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.dev1.deadpool.R
import mx.dev1.deadpool.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)

        initView()

        return binding.root
    }

    private fun initView() {
        binding.tatsiBtnRegisterSubmit.setOnClickListener {
            if(validateRegisterForm()) {
                Toast.makeText(requireContext(),
                    resources.getText(R.string.tatsi_test_form), Toast.LENGTH_LONG).show()
            }
        }

        binding.tatsiBtnLoginNavigate.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
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
            binding.tatsiTxtlEmailRegister.error = null
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