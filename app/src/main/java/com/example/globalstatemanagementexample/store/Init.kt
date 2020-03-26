package com.example.globalstatemanagementexample.store

typealias Mutate <State> = (State, Action) -> State
typealias Subscriber <State> = (State) -> Unit
interface State
interface Action

abstract class Store {
    abstract val mutate: Mutate<State>
    abstract fun dispatch(action: Action)
}