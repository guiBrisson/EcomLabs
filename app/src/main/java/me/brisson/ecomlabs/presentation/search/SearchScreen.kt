package me.brisson.ecomlabs.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme
import me.brisson.ecomlabs.util.AppTopBar
import me.brisson.ecomlabs.util.SearchInputText

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { AppTopBar(onBack = onBack) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            val uiState by viewModel.uiSate.collectAsState()

            SearchInputText(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                onSearch = { },
                text = uiState.searchInput
            )

            FilterChipGroup(
                chips = listOf(
                    "one",
                    "two",
                    "three",
                    "four",
                    "one",
                    "two",
                    "three",
                    "four"
                ), onClick = { })
        }
    }
}

@Preview
@ExperimentalMaterial3Api
@Composable
private fun PreviewSearchScreen() {
    EcomLabsTheme {
        SearchScreen(onBack = { })
    }
}
