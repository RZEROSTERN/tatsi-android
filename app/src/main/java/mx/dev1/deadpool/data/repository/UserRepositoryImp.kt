package mx.dev1.deadpool.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import mx.dev1.deadpool.domain.models.Response
import mx.dev1.deadpool.domain.models.User
import mx.dev1.deadpool.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    override suspend fun insertProfileInfo(user: User) {
        firestore.document(user.id).set(user)
        return
    }
}