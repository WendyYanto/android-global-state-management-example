package com.example.globalstatemanagementexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.globalstatemanagementexample.store.CounterAction
import com.example.globalstatemanagementexample.store.CounterStore

class MainFragment : Fragment() {

    private lateinit var incrementButton: Button
    private val counterStore by lazy {
        CounterStore.getInstance()
    }

    companion object {
        fun getInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incrementButton = view.findViewById(R.id.btn_increment_from_fragment)
        incrementButton.setOnClickListener {
            counterStore.dispatch(CounterAction.Increment)
        }
    }
}