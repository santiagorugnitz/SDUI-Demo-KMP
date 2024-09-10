package ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface BaseState
interface BaseEvent
interface BaseAction

abstract class BaseViewModel<State : BaseState, Event : BaseEvent, Action : BaseAction>(initialState: State) : ViewModel() {

    private val _stateFlow: MutableStateFlow<State> = MutableStateFlow(initialState)

    val stateFlow: StateFlow<State>
        get() = _stateFlow

    val state: State
        get() = _stateFlow.value

    abstract fun onEvent(event: Event)

    protected fun sendAction(action: Action) {
        val newState = reduce(action)
        _stateFlow.tryEmit(newState)
    }

    protected abstract fun reduce(action: Action): State

}
