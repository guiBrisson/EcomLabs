package me.brisson.ecomlabs.presentation.home

import me.brisson.ecomlabs.data.model.ProductsList
import me.brisson.ecomlabs.data.model.User
import me.brisson.ecomlabs.util.CurrentUser

data class HomeUiState(
    val showLogoutConfirmationDialog: Boolean = false,
    val isUserLoggedIn: Boolean = CurrentUser.isLoggedIn(),
    val currentUser: User? = CurrentUser(),
    val productLists: List<ProductsList> = emptyList()
)
