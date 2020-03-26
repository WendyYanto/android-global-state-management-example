package com.example.globalstatemanagementexample.store

data class CounterState(val value: Int = 0) : State

sealed class CounterAction : Action {
    object Increment : CounterAction()
    object Decrement : CounterAction()
}

class CounterStore : Store() {

    companion object {
        //ToDo Implement DI
        private var instance: CounterStore? = null
        fun getInstance(): CounterStore {
            return this.instance?.let {
                it
            } ?: run {
                this.instance = CounterStore()
                return this.instance!!
            }
        }
    }

    private val subscriptions = mutableSetOf<Subscriber<CounterState>>()

    private var state: CounterState = CounterState()
        set(value) {
            field = value
            subscriptions.forEach {
                it.invoke(value)
            }
        }

    override val mutate: Mutate<State> = { state, actions ->
        val data = state as CounterState
        when (actions) {
            is CounterAction.Increment -> data.copy(value = data.value + 1)
            is CounterAction.Decrement -> data.copy(value = data.value - 1)
            else -> data
        }
    }

    override fun dispatch(action: Action) {
        state = mutate(state, action) as CounterState
    }

    fun subscribe(subscriber: Subscriber<CounterState>) {
        this.subscriptions.add(subscriber)
    }

    fun unsubscribe(subscriber: Subscriber<CounterState>) {
        this.subscriptions.remove(subscriber)
    }

    fun clearSubscriber() {
        this.subscriptions.clear()
    }

    fun getState(): CounterState {
        return this.state
    }
}