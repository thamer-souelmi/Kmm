package org.example.project.domain.use_case

import org.example.project.domain.model.User
import org.example.project.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.login(email, password)
    }
}