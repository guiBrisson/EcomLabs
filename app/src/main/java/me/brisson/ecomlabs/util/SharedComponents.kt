package me.brisson.ecomlabs.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
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
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.arrow_back_content_description)
                )
            }
        },
        actions = actions
    )
}

@Composable
@ExperimentalMaterial3Api
fun SearchInputText(modifier: Modifier = Modifier, onSearch: (String) -> Unit, text: String? = "") {
    var input by remember { mutableStateOf(TextFieldValue(text ?: "")) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_input_label),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        value = input,
        onValueChange = { input = it },
        shape = RoundedCornerShape(18.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon_content_description)
            )
        },
        trailingIcon = {
            if (input.text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(id = R.string.clear_icon_content_description),
                    modifier = Modifier.clickable {
                        focusManager.clearFocus()
                        input = TextFieldValue("")
                    }
                )
            }
        },
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(input.text)
            focusManager.clearFocus()
        }),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
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

@ExperimentalMaterial3Api
@Preview
@Composable
private fun PreviewSearchInputText() {
    EcomLabsTheme {
        SearchInputText(onSearch = { })
    }
}
