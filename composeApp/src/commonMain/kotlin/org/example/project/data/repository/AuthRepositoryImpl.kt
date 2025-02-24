package org.example.project.data.repository

import org.example.project.domain.model.User
import org.example.project.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return if (email == "1" && password.isNotEmpty()) {
            Result.success(User(email, "dummy_token"))
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}