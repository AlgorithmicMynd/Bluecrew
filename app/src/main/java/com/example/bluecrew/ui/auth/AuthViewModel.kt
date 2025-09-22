package com.example.bluecrew.ui.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Sealed class for Registration State
sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

// NEW: Sealed class for Login State
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class AuthViewModel : ViewModel() {

    // --- REGISTRATION LOGIC (Existing) ---
    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState = _registrationState.asStateFlow()

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPass: String,
        role: String? = null // optional for User, required for Org
    ) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            delay(2000)

            if (name.isBlank() || email.isBlank() || password.isBlank()) {
                _registrationState.value = RegistrationState.Error("All fields are required.")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _registrationState.value = RegistrationState.Error("Please enter a valid email address.")
            } else if (password != confirmPass) {
                _registrationState.value = RegistrationState.Error("Passwords do not match.")
            } else if (role != null && role.isBlank()) {
                _registrationState.value = RegistrationState.Error("Please select an organisation type.")
            } else {
                // Here you can store/register user with backend
                _registrationState.value = RegistrationState.Success
            }
        }
    }


    fun resetRegistrationState() {
        _registrationState.value = RegistrationState.Idle
    }

    // --- LOGIN LOGIC (New) ---
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            // Simulate network call
            delay(2000)

            // 1. Check for blank fields
            if (email.isBlank() || password.isBlank()) {
                _loginState.value = LoginState.Error("Email and password cannot be blank.")
            }
            // 2. Check for the specific mock credentials
            else if (email.equals("test@gmail.com" , ignoreCase = true) && password == "123456") {
                _loginState.value = LoginState.Success
            }
            // 3. If credentials don't match
            else {
                _loginState.value = LoginState.Error("Invalid email or password.")
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }
    fun resetState(){
        _registrationState.value = RegistrationState.Idle
    }
}
