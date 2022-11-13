package me.brisson.ecomlabs.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.brisson.ecomlabs.AppDestinationsArgs.SEARCH_INPUT_ARGS
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val input: String? = savedStateHandle[SEARCH_INPUT_ARGS]

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiSate: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        input?.let { populateSearchInput(it) }
    }

    private fun populateSearchInput(input: String) {
        _uiState.update { it.copy(searchInput = input) }
    }
}
