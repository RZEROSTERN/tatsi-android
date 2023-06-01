package mx.dev1.deadpool.domain.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import mx.dev1.deadpool.domain.models.Response

typealias AuthStateResponse = StateFlow<Boolean>
typealias SignInResponse = Response<Boolean>
typealias SignOutResponse = Response<Boolean>


interface AuthRepository {
    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse

    suspend fun firebaseSignInAnonymously(): SignInResponse

    suspend fun firebaseSignOut(): SignOutResponse
}