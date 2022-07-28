package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SharedPreferenceActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharedpreference)
        val inputId = findViewById<EditText>(R.id.editId)
        val inputName = findViewById<EditText>(R.id.editName)
        val outputId = findViewById<TextView>(R.id.textViewShowId)
        val outputName = findViewById<TextView>(R.id.textViewShowName)
        val btnSave = findViewById<Button>(R.id.save)
        val btnView = findViewById<Button>(R.id.view)
        val btnClear = findViewById<Button>(R.id.clear)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        btnSave.setOnClickListener(View.OnClickListener {
            val id:Int = Integer.parseInt(inputId.text.toString())
            val name:String = inputName.text.toString()
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            editor.commit()
        })
        btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key",0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if(sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                outputName.setText("default name: ${sharedNameValue}").toString()
                outputId.setText("default id: ${sharedIdValue.toString()}")
            }else{
                outputName.setText(sharedNameValue).toString()
                outputId.setText(sharedIdValue.toString())
            }

        }
        btnClear.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            outputName.setText("").toString()
            outputId.setText("".toString())
        })
    }
}