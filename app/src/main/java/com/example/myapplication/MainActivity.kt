package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {

    val permssoinCode: Int = 200


    private val itemsList = ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "RecyclerView - Bajaj Training"

        val floatButton:FloatingActionButton=findViewById(R.id.floatingActionButton)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        customAdapter = CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()

        lifecycleScope.launch {
            while (true){
                delay(1000L)
                Log.d(TAG,"Still running...")
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            delay(5000L)
            //Toast.makeText(applicationContext, "Coroutine Scope is Launched", Toast.LENGTH_SHORT).show()
        }
        floatButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT)
        })

    }
    private fun prepareItems() {
        itemsList.add("Item 1")
        itemsList.add("Item 2")
        itemsList.add("Item 3")
        itemsList.add("Item 4")
        itemsList.add("Item 5")
        itemsList.add("Item 6")
        itemsList.add("Item 7")
        itemsList.add("Item 8")
        itemsList.add("Item 9")
        itemsList.add("Item 10")
        itemsList.add("Item 11")
        itemsList.add("Item 12")
        itemsList.add("Item 13")
        customAdapter.notifyDataSetChanged()
    }


}