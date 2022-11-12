package me.brisson.ecomlabs.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.brisson.ecomlabs.data.model.CEP
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme
import me.brisson.ecomlabs.util.CurrentUser

@Composable
@ExperimentalMaterial3Api
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    if (CurrentUser.isLoggedIn()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                CurrentUser()?.let { user ->
                    HomeProfileDrawer(
                        modifier = Modifier.width(DrawerDefaults.MaximumDrawerWidth - 40.dp),
                        user = user,
                        onAccountDetail = {},
                        onOrders = {},
                        onAddress = {},
                        onPayment = {},
                        onNotifications = {},
                        onLogout =  viewModel::showLogoutConfirmationDialog
                    )
                }

            }
        ) {
            if (uiState.showLogoutConfirmationDialog) {
                LogoutConfirmationDialog { confirmed ->
                    if (confirmed) {
                        scope.launch { drawerState.close() }
                        CurrentUser.logout()
                    }
                    viewModel.closeLogoutConfirmationDialog()
                }
            }
            HomeContent(modifier = modifier, scope = scope, drawerState = drawerState)
        }
    } else {
        HomeContent(modifier = modifier, scope = scope, drawerState = drawerState)
    }

}

@ExperimentalMaterial3Api
@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize()) {
        HomeAppBar(
            cep = CEP("12345-123"),
            onSearch = {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }, onCep = {
                Toast.makeText(context, it.value, Toast.LENGTH_SHORT).show()
            }
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {

        }
        HomeBottomAppBar(
            onProfile = {
                if (CurrentUser.isLoggedIn()) {
                    scope.launch { drawerState.open() }
                }
            },
            onSearch = {  },
            onShoppingBag = { }
        )
    }
}

@Composable
@ExperimentalMaterial3Api
@Preview
private fun PreviewHomeScreen() {
    EcomLabsTheme {
        HomeScreen()
    }
}

