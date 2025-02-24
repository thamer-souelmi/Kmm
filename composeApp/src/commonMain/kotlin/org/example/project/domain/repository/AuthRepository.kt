package org.example.project.domain.repository

import org.example.project.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
}