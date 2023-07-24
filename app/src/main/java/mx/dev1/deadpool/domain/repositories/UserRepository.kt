package mx.dev1.deadpool.domain.repositories

import mx.dev1.deadpool.domain.models.Response
import mx.dev1.deadpool.domain.models.User

interface UserRepository {
    suspend fun insertProfileInfo(
        user: User
    )
}
