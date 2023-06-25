package mx.dev1.deadpool.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import mx.dev1.deadpool.domain.models.Response
import mx.dev1.deadpool.domain.models.Response.Success
import mx.dev1.deadpool.domain.models.Response.Failure
import mx.dev1.deadpool.domain.models.User
import mx.dev1.deadpool.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener { authStateListener }
        awaitClose {
            auth.removeAuthStateListener { authStateListener }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)

    override suspend fun firebaseSignIn(email: String, password: String) = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Success(true)
    } catch(e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignUp(
        email: String, password: String,
        firstName: String, lastName: String, phone: String
    ) = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        val user = auth.currentUser
        Success(user)
    } catch(e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignOut() = try {
        auth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}