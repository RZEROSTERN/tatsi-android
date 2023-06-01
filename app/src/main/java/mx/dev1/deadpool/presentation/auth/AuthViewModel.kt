package mx.dev1.deadpool.presentation.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.dev1.deadpool.domain.usecases.UseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    fun signIn() {}
}