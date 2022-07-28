package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_coroutines)
        val buttonCall: Button = findViewById(R.id.button_callme)
        val dummyText:TextView=findViewById(R.id.textView)
        buttonCall.setOnClickListener(View.OnClickListener {
            val job = GlobalScope.launch(Dispatchers.Default) {
                repeat(5) {
                    Log.d(TAG, "Coroutines is still working...")
                    delay(1000L)
                }
            }

            GlobalScope.launch {
                val networkCallAnswer = doNetworkCall()
                val networkCallAnswer2 = doNetworkCall2()
                Log.d(TAG, networkCallAnswer)
                Log.d(TAG, networkCallAnswer2)
                //delay(5000L)
                //Log.d(TAG, "Coroutines says hello from thread ${Thread.currentThread().name}")
            }
            lifecycleScope.launch(Dispatchers.IO) {
                Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
                val answer = doNetworkCall()
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                    dummyText.text = answer
                }
            }
            Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")
        })
    }
    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }

    private suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "This is the answer2"
    }
}