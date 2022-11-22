package me.brisson.ecomlabs.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.brisson.ecomlabs.data.model.ProductsList
import me.brisson.ecomlabs.data.model.mockedProductList
import me.brisson.ecomlabs.util.CurrentUser
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(productLists = listOf(mockedProductList))
        }
    }

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

    fun userLogout() {
        CurrentUser.logout()
        _uiState.update {
            it.copy(isUserLoggedIn = false)
        }
    }
}
