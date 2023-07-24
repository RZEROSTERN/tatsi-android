package mx.dev1.deadpool.domain.usecases.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import mx.dev1.deadpool.domain.repositories.AuthRepository

class GetAuthState(
    private val repo: AuthRepository
) {
    operator fun invoke(
        viewModelScope: CoroutineScope
    ): StateFlow<Boolean> = repo.getAuthState(viewModelScope)
}
