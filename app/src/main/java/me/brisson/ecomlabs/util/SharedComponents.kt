package me.brisson.ecomlabs.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.ecomlabs.R
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme

@Composable
@Preview
fun NoInternetConnectionComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.inverseSurface)
            .padding(horizontal = 20.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(28.dp)
                .padding(end = 10.dp),
            imageVector = Icons.Default.WifiOff,
            contentDescription = stringResource(id = R.string.no_wifi_icon_content_description),
            tint = MaterialTheme.colorScheme.inverseOnSurface,
        )
        Text(
            text = stringResource(id = R.string.no_internet_connection),
            color = MaterialTheme.colorScheme.inverseOnSurface,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@ExperimentalMaterial3Api
fun AppTopBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = stringResource(id = R.string.search_appbar_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.arrow_back_content_description)
                )
            }
        },
        windowInsets = WindowInsets(left = 20.dp, right = 20.dp),
        actions = actions
    )
}



@Preview
@ExperimentalMaterial3Api
@Composable
fun PreviewTopAppBar() {
    EcomLabsTheme {
        AppTopBar(onBack = { })
    }
}
