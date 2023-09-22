package com.thananonp.multipleactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        val exampleObject = intent.extras?.getParcelable<ExampleObject>("People")

        val textField = findViewById<TextView>(R.id.textView2)
        exampleObject?.let {
            textField.text = "My name is ${it.name} ${it.surname}"
        } ?: run {
            textField.text = "There is nothing!!!"
        }

        val button = findViewById<Button>(R.id.button_forn_finish)
        button.setOnClickListener {
            val text = findViewById<EditText>(R.id.editTextText).text.toString()
            val finishIntent = Intent().apply {
                putExtra("Form", text)
            }

            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }
}