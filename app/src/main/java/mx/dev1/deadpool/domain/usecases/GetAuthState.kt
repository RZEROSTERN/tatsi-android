package mx.dev1.deadpool.domain.usecases

import kotlinx.coroutines.CoroutineScope
import mx.dev1.deadpool.domain.repositories.AuthRepository
import mx.dev1.deadpool.domain.repositories.AuthStateResponse

class GetAuthState(
    private val repo: AuthRepository
) {
    operator fun invoke(
        viewModelScope: CoroutineScope
    ): AuthStateResponse = repo.getAuthState(viewModelScope)
}