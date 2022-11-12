package me.brisson.ecomlabs.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun showLogoutConfirmationDialog() {
        _uiState.update {
            it.copy(showLogoutConfirmationDialog = true)
        }
    }

    fun closeLogoutConfirmationDialog() {
        _uiState.update {
            it.copy(showLogoutConfirmationDialog = false)
        }
    }
}
