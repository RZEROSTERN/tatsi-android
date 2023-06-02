package mx.dev1.deadpool.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.dev1.deadpool.domain.models.Response
import mx.dev1.deadpool.domain.models.Response.Loading
import mx.dev1.deadpool.domain.usecases.UseCases
import javax.inject.Inject
import mx.dev1.deadpool.domain.models.Response.Success
import mx.dev1.deadpool.domain.models.Response.Failure

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val signInResponseMutable = MutableLiveData<Response<Any>>()
    val signInResponse: LiveData<Response<Any>> get() = signInResponseMutable

    private val signUpResponseMutable = MutableLiveData<Response<Any>>()
    val signUpResponse: LiveData<Response<Any>> get() = signUpResponse
    private val signOutResponseMutable = MutableLiveData<Response<Any>>()
    val signOutResponse: LiveData<Response<Any>> get() = signOutResponseMutable
    private val errorResponseMutable = MutableLiveData<Failure>()
    val errorResponse: LiveData<Failure> get() = errorResponseMutable
    fun signIn(email: String, password: String) = viewModelScope.launch {
        signInResponseMutable.postValue(Loading)
        val response = useCases.signIn(email, password)

        if(response is Success) {
            signInResponseMutable.postValue(response)
        } else if(response is Failure) {
            errorResponseMutable.postValue(response)
        }
    }

    fun signOut() = viewModelScope.launch {
        signOutResponseMutable.postValue(Loading)
        signOutResponseMutable.postValue(useCases.signOut())
    }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        signInResponseMutable.postValue(Loading)
        val response = useCases.signUp(email, password)

        if(response is Success) {
            signInResponseMutable.postValue(response)
        } else if(response is Failure) {
            errorResponseMutable.postValue(response)
        }
    }
}