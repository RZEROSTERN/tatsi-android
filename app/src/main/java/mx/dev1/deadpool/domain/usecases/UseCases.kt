package mx.dev1.deadpool.domain.usecases

data class UseCases(
    val getAuthState: GetAuthState,
    val signIn: SignIn,
    val signOut: SignOut
)
