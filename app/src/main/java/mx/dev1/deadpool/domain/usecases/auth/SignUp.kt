package mx.dev1.deadpool.domain.usecases.auth

import mx.dev1.deadpool.domain.repositories.AuthRepository

class SignUp(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        repo.firebaseSignUp(email, password, firstName = "", lastName = "", phone = "")
}