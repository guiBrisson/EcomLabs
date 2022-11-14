package me.brisson.ecomlabs.presentation.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.ecomlabs.ui.theme.EcomLabsTheme

@ExperimentalMaterial3Api
@Composable
fun FilterChipGroup(
    modifier: Modifier = Modifier,
    chips: List<String>,
    onClick: (String) -> Unit
) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(horizontal = 10.dp)) {
        items(chips) { chip ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = chip,
                onSelected = onClick
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun FilterChip(
    modifier: Modifier = Modifier,
    text: String,
    onSelected: (String) -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = {
            selected = !selected
            if (selected) {
                onSelected(text)
            }
        },
        label = { Text(text) },
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Localized Description",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else null
    )
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
private fun PreviewFilterChip() {
    EcomLabsTheme {
        FilterChipGroup(
            chips = listOf("one", "two", "three", "four"),
            onClick = {}
        )
    }
}
