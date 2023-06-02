package mx.dev1.deadpool.domain.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import mx.dev1.deadpool.domain.models.Response

interface AuthRepository {
    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>
    suspend fun firebaseSignInAnonymously(): Response<Boolean>
    suspend fun firebaseSignOut(): Response<Boolean>
    suspend fun firebaseSignIn(email: String, password: String): Response<Boolean>

    suspend fun firebaseSignUp(email: String, password: String): Response<Boolean>
}