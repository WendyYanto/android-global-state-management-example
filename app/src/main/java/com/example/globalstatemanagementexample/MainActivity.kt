package com.example.globalstatemanagementexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.globalstatemanagementexample.store.CounterStore
import com.example.globalstatemanagementexample.store.CounterAction
import com.example.globalstatemanagementexample.store.CounterState

class MainActivity : AppCompatActivity() {

    private lateinit var incrementButton: Button
    private lateinit var decrementButton: Button
    private lateinit var counterText: TextView
    private val counterStore by lazy {
        CounterStore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        incrementButton = findViewById(R.id.btn_increment)
        decrementButton = findViewById(R.id.btn_decrement)
        counterText = findViewById(R.id.counter)

        supportFragmentManager.beginTransaction()
            .add(R.id.fl_fragment, MainFragment.getInstance())
            .commit()

        counterStore.subscribe(::showCounter)

        incrementButton.setOnClickListener {
            counterStore.dispatch(CounterAction.Increment)
        }

        decrementButton.setOnClickListener {
            counterStore.dispatch(CounterAction.Decrement)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        counterStore.unsubscribe(::showCounter)
    }

    override fun onResume() {
        super.onResume()
        this.showCounter(counterStore.getState())
    }

    private fun showCounter(state: CounterState) {
        counterText.text = state.value.toString()
    }

}
