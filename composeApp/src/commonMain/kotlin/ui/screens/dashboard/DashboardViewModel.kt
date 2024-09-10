package ui.screens.dashboard

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import domain.models.server_driven.ServerDrivenScreen
import domain.repositories.ActionResult
import domain.repositories.IRepository
import domain.repositories.MockRepository
import infrastructure.toDomainModelClass
import jsonFormatter
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonObject
import models.ServerDrivenScreenResponse
import ui.base.BaseAction
import ui.base.BaseEvent
import ui.base.BaseState
import ui.base.BaseViewModel
import ui.base.LoadState

class DashboardViewModel(
    private val repository: IRepository = MockRepository //TODO: change after demo
) : BaseViewModel<DashboardViewModel.State, DashboardViewModel.UIEvent, DashboardViewModel.Action>(State()) {

    init {
        sendAction(Action.EndpointChange(state.selectedEndpoint))
    }

    private fun loadJson() {
        sendAction(Action.Loading)
        viewModelScope.launch {
            when (val result = repository.getRawResponse(state.selectedEndpoint)) {
                is ActionResult.Error -> sendAction(Action.Failure(result.error))
                is ActionResult.Success -> sendAction(Action.JsonChange(result.value))
            }
        }
    }

    private fun saveJson() {
        sendAction(Action.SavingLoading)
        if (state.jsonError.isEmpty()) {
            viewModelScope.launch {
                when (repository.updateResponse(state.selectedEndpoint, state.json)) {
                    is ActionResult.Error -> sendAction(Action.SavingFailure)
                    is ActionResult.Success -> sendAction(Action.SavingSuccess)
                }
            }
        }
    }

    override fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.JsonChange -> sendAction(Action.JsonChange(event.value))
            is UIEvent.EndpointSelect -> sendAction(Action.EndpointChange(event.value))
            is UIEvent.EndpointCall -> loadJson()
            is UIEvent.EndpointSave -> saveJson()
        }
    }

    override fun reduce(action: Action): State {
        return when (action) {
            is Action.JsonChange -> {
                try {
                    val response = jsonFormatter.decodeFromString<ServerDrivenScreenResponse>(action.value)
                    state.copy(
                        loadState = LoadState.ShowContent,
                        json = jsonFormatter.encodeToString(response),
                        components = response.toDomainModelClass(),
                        jsonError = ""
                    )
                } catch (ex: Exception) {
                    try {
                        val entities = jsonFormatter.decodeFromString<JsonObject>(action.value)
                        state.copy(
                            loadState = LoadState.ShowContent,
                            json = jsonFormatter.encodeToString(entities),
                            jsonError = ex.message.orEmpty()
                        )
                    } catch (ex: Exception) {
                        state.copy(
                            loadState = LoadState.ShowContent,
                            json = action.value,
                            jsonError = ex.message.orEmpty()
                        )
                    }
                }
            }

            is Action.EndpointChange -> {
                val json = repository.getFromCache(action.value)
                val components = try {
                    val response = jsonFormatter.decodeFromString<ServerDrivenScreenResponse>(json)
                    response.toDomainModelClass()
                } catch (ex: Exception) {
                    null
                }
                state.copy(
                    selectedEndpoint = action.value,
                    json = json,
                    components = components ?: state.components
                )
            }

            is Action.Loading -> state.copy(
                loadState = LoadState.Loading,
                json = "",
                jsonError = "",
                components = null,
            )

            is Action.Failure -> state.copy(
                loadState = LoadState.Error(action.error) { loadJson() },
                json = "",
                jsonError = "",
                components = null,
            )

            is Action.SavingLoading -> state.copy(
                savingLoading = true
            )

            is Action.SavingFailure -> state.copy(
                savingLoading = false
            )

            is Action.SavingSuccess -> state.copy(
                savingLoading = false
            )
        }
    }

    @Immutable
    sealed class UIEvent : BaseEvent {
        data class JsonChange(val value: String) : UIEvent()
        data class EndpointSelect(val value: Endpoint) : UIEvent()
        data object EndpointCall : UIEvent()
        data object EndpointSave : UIEvent()
    }

    @Immutable
    sealed class Action : BaseAction {
        data object Loading : Action()
        data object SavingLoading : Action()
        data object SavingSuccess : Action()
        data object SavingFailure : Action()
        data class Failure(val error: String) : Action()
        data class EndpointChange(val value: Endpoint) : Action()
        data class JsonChange(val value: String) : Action()
    }

    @Immutable
    data class State(
        val loadState: LoadState = LoadState.Empty,
        val json: String = "",
        val jsonError: String = "",
        val components: ServerDrivenScreen? = null,
        val selectedEndpoint: Endpoint = Endpoint.HOME,
        val savingLoading: Boolean = false,
    ) : BaseState

}

