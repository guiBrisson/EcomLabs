package me.brisson.ecomlabs.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import me.brisson.ecomlabs.data.model.Product
import me.brisson.ecomlabs.data.model.ProductsList
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme
import me.brisson.ecomlabs.util.CurrentUser

@Composable
@ExperimentalMaterial3Api
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onSearch: (String) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isUserLoggedIn) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                uiState.currentUser?.let { user ->
                    HomeProfileDrawer(
                        modifier = Modifier.width(DrawerDefaults.MaximumDrawerWidth - 40.dp),
                        user = user,
                        onAccountDetail = {},
                        onOrders = {},
                        onAddress = {},
                        onPayment = {},
                        onNotifications = {},
                        onLogout = viewModel::showLogoutConfirmationDialog
                    )
                }

            }
        ) {
            if (uiState.showLogoutConfirmationDialog) {
                LogoutConfirmationDialog { confirmed ->
                    if (confirmed) {
                        scope.launch { drawerState.close() }
                        viewModel.userLogout()
                    }
                    viewModel.closeLogoutConfirmationDialog()
                }
            }
            HomeContent(
                modifier = modifier,
                scope = scope,
                drawerState = drawerState,
                onSearch = onSearch,
                productLists = uiState.productLists,
                onProduct = { }
            )
        }
    } else {
        HomeContent(
            modifier = modifier,
            scope = scope,
            drawerState = drawerState,
            onSearch = onSearch,
            productLists = uiState.productLists,
            onProduct = { }
        )
    }

}

@ExperimentalMaterial3Api
@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onSearch: (String) -> Unit,
    productLists: List<ProductsList>,
    onProduct: (Product) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            HomeAppBar(
                cep = CEP("12345-123"),
                onSearch = onSearch,
                onCep = {
                    Toast.makeText(context, it.value, Toast.LENGTH_SHORT).show()
                }
            )
        },
        bottomBar = {
            HomeBottomAppBar(
                onProfile = {
                    if (CurrentUser.isLoggedIn()) {
                        scope.launch { drawerState.open() }
                    }
                },
                onSearch = { onSearch("") },
                onShoppingBag = { }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            List(
                productLists = productLists,
                onProduct = onProduct
            )
        }
    }
}

@Composable
@ExperimentalMaterial3Api
@Preview(showBackground = true)
private fun PreviewHomeScreen() {
    EcomLabsTheme {
        HomeScreen(onSearch = { })
    }
}

