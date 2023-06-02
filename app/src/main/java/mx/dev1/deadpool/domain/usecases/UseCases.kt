package mx.dev1.deadpool.domain.usecases

import mx.dev1.deadpool.domain.usecases.auth.GetAuthState
import mx.dev1.deadpool.domain.usecases.auth.SignIn
import mx.dev1.deadpool.domain.usecases.auth.SignOut
import mx.dev1.deadpool.domain.usecases.auth.SignUp

data class UseCases(
    val getAuthState: GetAuthState,
    val signIn: SignIn,
    val signOut: SignOut,
    val signUp: SignUp
)
