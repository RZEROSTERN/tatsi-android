package mx.dev1.deadpool.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.dev1.deadpool.domain.models.Response.Loading
import mx.dev1.deadpool.domain.models.Response.Success
import mx.dev1.deadpool.domain.repositories.SignInResponse
import mx.dev1.deadpool.domain.repositories.SignOutResponse
import mx.dev1.deadpool.domain.usecases.UseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val signInResponseMutable = MutableLiveData<SignInResponse>(Success(false))
    val signInResponse: LiveData<SignInResponse> get() = signInResponseMutable
    private val signOutResponseMutable = MutableLiveData<SignOutResponse>(Success(false))
    val signOutResponse: LiveData<SignOutResponse> get() = signOutResponseMutable
    fun signIn() = viewModelScope.launch {
        signInResponseMutable.postValue(Loading)
        signInResponseMutable.postValue(useCases.signIn())
    }

    fun signOut() = viewModelScope.launch {
        signOutResponseMutable.postValue(Loading)
        signOutResponseMutable.postValue(useCases.signOut())
    }
}