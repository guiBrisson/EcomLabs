package me.brisson.ecomlabs.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import me.brisson.ecomlabs.R
import me.brisson.ecomlabs.data.model.*
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme
import me.brisson.ecomlabs.util.HorizontalProduct
import me.brisson.ecomlabs.util.SearchInputText
import me.brisson.ecomlabs.util.VerticalProduct
import java.math.BigDecimal

@Composable
@ExperimentalMaterial3Api
fun HomeAppBar(
    modifier: Modifier = Modifier,
    cep: CEP? = null,
    onCep: (CEP) -> Unit,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(
                top = 20.dp,
                bottom = 10.dp
            )
    ) {
        SearchInputText(modifier = Modifier.padding(horizontal = 20.dp), onSearch = onSearch)
        cep?.let { CEP ->
            CEPComponent(
                modifier = Modifier.padding(top = 8.dp, start = 10.dp, end = 10.dp),
                cep = CEP,
                onClick = onCep
            )
        }
    }
}


@Composable
fun CEPComponent(
    modifier: Modifier = Modifier,
    cep: CEP,
    onClick: (CEP) -> Unit
) {
    val color = MaterialTheme.colorScheme.onSecondary
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .clickable { onClick(cep) }
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = stringResource(id = R.string.location_icon_content_description),
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = stringResource(id = R.string.cep_home_header, cep.value),
            style = MaterialTheme.typography.bodySmall,
            color = color,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.arrow_right_icon_content_description),
            tint = color
        )
    }
}

@Composable
fun HomeBottomAppBar(
    modifier: Modifier = Modifier,
    onProfile: () -> Unit,
    onSearch: () -> Unit,
    onShoppingBag: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 4.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        IconButton(onClick = onProfile, modifier = Modifier.size(48.dp)) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = stringResource(id = R.string.person_icon_content_description),
            )
        }
        IconButton(onClick = onSearch, modifier = Modifier.size(48.dp)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon_content_description)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .padding(end = 12.dp),
            onClick = onShoppingBag,
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = stringResource(id = R.string.shopping_icon_content_description)
            )
        }
    }
}


@Composable
fun HomeProfileDrawer(
    modifier: Modifier = Modifier,
    user: User,
    onAccountDetail: () -> Unit,
    onOrders: () -> Unit,
    onAddress: () -> Unit,
    onPayment: () -> Unit,
    onNotifications: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseSurface)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.profile_image_content_description),
                loading = { CircularProgressIndicator() },
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(75.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        DrawerItem(
            modifier = Modifier.padding(10.dp),
            icon = Icons.Default.Person,
            contentDescription = stringResource(id = R.string.person_icon_content_description),
            text = stringResource(id = R.string.change_account_detail),
            color = MaterialTheme.colorScheme.onBackground,
            onClick = onAccountDetail
        )
        DrawerItem(
            modifier = Modifier.padding(10.dp),
            icon = Icons.Default.RequestPage,
            contentDescription = stringResource(id = R.string.person_icon_content_description),
            text = stringResource(id = R.string.my_orders),
            color = MaterialTheme.colorScheme.onBackground,
            onClick = onOrders
        )
        DrawerItem(
            modifier = Modifier.padding(10.dp),
            icon = Icons.Filled.Home,
            contentDescription = stringResource(id = R.string.person_icon_content_description),
            text = stringResource(id = R.string.my_address),
            color = MaterialTheme.colorScheme.onBackground,
            onClick = onAddress
        )
        DrawerItem(
            modifier = Modifier.padding(10.dp),
            icon = Icons.Filled.CreditCard,
            contentDescription = stringResource(id = R.string.person_icon_content_description),
            text = stringResource(id = R.string.payment_methods),
            color = MaterialTheme.colorScheme.onBackground,
            onClick = onPayment
        )
        DrawerItem(
            modifier = Modifier.padding(10.dp),
            icon = Icons.Filled.Notifications,
            contentDescription = stringResource(id = R.string.person_icon_content_description),
            text = stringResource(id = R.string.notification),
            color = MaterialTheme.colorScheme.onBackground,
            onClick = onNotifications
        )
        Spacer(modifier = Modifier.weight(1f))
        DrawerItem(
            modifier = Modifier.padding(10.dp),
            icon = Icons.Default.Logout,
            contentDescription = stringResource(id = R.string.person_icon_content_description),
            text = stringResource(id = R.string.logout),
            color = MaterialTheme.colorScheme.error,
            hasTrailingIcon = false,
            onClick = onLogout
        )

    }
}

@Composable
private fun DrawerItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    text: String,
    onClick: () -> Unit,
    color: Color,
    hasTrailingIcon: Boolean = true
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier.padding(end = 15.dp),
            imageVector = icon,
            contentDescription = contentDescription,
            tint = color
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = color
        )
        if (hasTrailingIcon) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.arrow_right_icon_content_description),
                tint = color
            )
        }
    }

}

@Composable
fun LogoutConfirmationDialog(
    onConfirmCallback: (Boolean) -> Unit
) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.logout))
        },
        text = {
            Text(text = stringResource(id = R.string.logout_confirmation))
        },
        confirmButton = {
            TextButton(onClick = { onConfirmCallback(true) }) {
                Text(text = stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onConfirmCallback(false) }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        onDismissRequest = { onConfirmCallback(false) },
    )
}

@Composable
fun ProductListTitle(modifier: Modifier = Modifier, title: String, onArrow: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        IconButton(onClick = onArrow) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.arrow_right_icon_content_description)
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    product: Product,
    type: ProductComponentType,
    onProduct: () -> Unit
) {
    when (type) {
        ProductComponentType.HORIZONTAL -> {

        }
        ProductComponentType.VERTICAL -> {
            VerticalProduct(
                modifier = modifier,
                product = product,
                onClick = onProduct
            )
        }
        ProductComponentType.BIG -> {}
    }
}

@ExperimentalMaterial3Api
@Composable
fun List(
    productLists: List<ProductsList>,
    onProduct: (Product) -> Unit
) {
    for (list in productLists) {
        when (list.componentType) {
            ProductComponentType.HORIZONTAL -> {
                HorizontalProductList(list = list, onProduct = onProduct)
            }
            ProductComponentType.VERTICAL -> {
                VerticalProductList(list = list, onProduct = onProduct)
            }
            ProductComponentType.BIG -> {}
        }

    }
}

@ExperimentalMaterial3Api
@Composable
private fun HorizontalProductList(
    modifier: Modifier = Modifier,
    list: ProductsList,
    onProduct: (Product) -> Unit
) {
    LazyColumn(modifier = modifier) {
        item {
            ProductListTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                title = list.title,
                onArrow = { })
        }
        items(list.products) { product ->
            HorizontalProduct(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp),
                product = product,
                onClick = { onProduct(product) }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun VerticalProductList(
    modifier: Modifier = Modifier,
    list: ProductsList,
    onProduct: (Product) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ProductListTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            title = list.title,
            onArrow = { }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {

            items(list.products) { product ->
                VerticalProduct(
                    modifier = Modifier.padding(6.dp),
                    product = product,
                    onClick = { onProduct(product) }
                )
            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
@Preview(group = "appbars")
private fun PreviewHomeAppBar() {
    EcomLabsTheme {
        HomeAppBar(cep = CEP("12345-123"), onCep = { }, onSearch = { })
    }
}

@Preview(showBackground = true, group = "appbars")
@Composable
private fun PreviewHomeBottomAppBar() {
    EcomLabsTheme {
        HomeBottomAppBar(
            onProfile = { },
            onSearch = { },
            onShoppingBag = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeProfileDrawer() {
    EcomLabsTheme {
        HomeProfileDrawer(
            user = mockedUser,
            onAccountDetail = {},
            onOrders = {},
            onAddress = {},
            onPayment = {},
            onNotifications = {},
            onLogout = {},
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewProductList() {
    EcomLabsTheme {
        Column {
            ProductListTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                title = "title one two",
                onArrow = { })
            ProductList(
                product = Product(
                    name = "nome",
                    image = "",
                    currentPrice = BigDecimal(200),
                    oldPrices = listOf(BigDecimal(250))
                ),
                type = ProductComponentType.HORIZONTAL,
                onProduct = { }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLogoutConfirmationDialog() {
    EcomLabsTheme {
        LogoutConfirmationDialog(onConfirmCallback = { })
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
private fun PreviewVerticalProductList() {
    EcomLabsTheme {
        VerticalProductList(
            list = mockedProductVerticalList,
            onProduct = {  }
        )
    }
}