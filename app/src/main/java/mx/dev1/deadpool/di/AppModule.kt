package mx.dev1.deadpool.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev1.deadpool.data.repository.AuthRepositoryImp
import mx.dev1.deadpool.domain.repositories.AuthRepository
import mx.dev1.deadpool.domain.usecases.GetAuthState
import mx.dev1.deadpool.domain.usecases.SignIn
import mx.dev1.deadpool.domain.usecases.SignOut
import mx.dev1.deadpool.domain.usecases.SignUp
import mx.dev1.deadpool.domain.usecases.UseCases

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository = AuthRepositoryImp(auth)

    @Provides
    fun provideUseCases(
        repo: AuthRepository
    ) = UseCases(
        getAuthState = GetAuthState(repo),
        signIn = SignIn(repo),
        signOut = SignOut(repo),
        signUp = SignUp(repo)
    )
}