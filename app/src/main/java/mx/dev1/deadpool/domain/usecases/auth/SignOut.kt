package mx.dev1.deadpool.domain.usecases.auth

import mx.dev1.deadpool.domain.repositories.AuthRepository

class SignOut(
    private val repo: AuthRepository
) {
    suspend operator fun invoke() = repo.firebaseSignOut()
}
