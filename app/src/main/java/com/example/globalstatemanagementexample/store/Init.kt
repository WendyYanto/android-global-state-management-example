package com.example.globalstatemanagementexample.store

typealias Subscriber <State> = (State) -> Unit
interface State
interface Action

abstract class Store {
    abstract fun mutate(state: State, action: Action): State
    abstract fun dispatch(action: Action)
}