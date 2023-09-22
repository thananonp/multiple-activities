package com.thananonp.multipleactivity

import android.R.attr
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formFragmentCallback =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val form = result.data?.getStringExtra("Form")
                    findViewById<TextView>(R.id.textView).text = form
                }
            }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val sendIntent = Intent(this, FormActivity::class.java).apply {
                putExtra(
                    "People", ExampleObject(
                        "Thananon", "Pongsuwan", 24
                    )
                )
            }
            formFragmentCallback.launch(sendIntent)
        }

        findViewById<Button>(R.id.button_camera).setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }

        findViewById<Button>(R.id.button_2).setOnClickListener {
            val sendIntent = Intent(this, FormActivity::class.java)
            formFragmentCallback.launch(sendIntent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            // Set the image in imageview for display
            // Set the image in imageview for display
            val photo = data?.extras?.get("data")
            if (photo is Bitmap) {
                findViewById<ImageView>(R.id.image_taken).setImageBitmap(photo)
            }
        }
    }
}