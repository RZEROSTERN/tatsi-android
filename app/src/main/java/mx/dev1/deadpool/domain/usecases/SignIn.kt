package mx.dev1.deadpool.domain.usecases

import mx.dev1.deadpool.domain.repositories.AuthRepository

class SignIn(
    private val repo: AuthRepository
) {
    suspend operator fun invoke() = repo.firebaseSignInAnonymously()
}