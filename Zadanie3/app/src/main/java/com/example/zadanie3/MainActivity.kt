package com.example.zadanie3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    companion object {
        const val CAMERA_PIC_REQUEST = 1
    }
    private lateinit var settingsButton: Button
    private lateinit var geoButton: Button
    private lateinit var photoButton: Button
    private lateinit var latText: EditText
    private lateinit var lonText: EditText
    private lateinit var my_image: ImageView

    val REQUEST_IMAGE_CAPTURE = 1
    val locationForPhotos = Uri.parse("D:\\All\\Studia\\Zajecia\\Semestr_6\\Zadanie3\\photo.png")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        latText = findViewById(R.id.Lat)
        lonText = findViewById(R.id.Lon)

        geoButton = findViewById(R.id.geoBtn)
        geoButton.setOnClickListener{geo()}

        settingsButton = findViewById(R.id.settingsBtn)
        settingsButton.setOnClickListener {
            settings()
        }

        photoButton = findViewById(R.id.photoBtn)
        photoButton.setOnClickListener { takePhoto()}


        my_image = findViewById(R.id.image)

    }
    private fun settings()
    {
        val intent = Intent(Settings.ACTION_SETTINGS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
    private fun geo() {
        val lat = latText.text.toString()
        val lon = lonText.text.toString()

        val uri = Uri.parse("geo:0,0?q=${lon},${lat}(Location)")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        //if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        //}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_PIC_REQUEST) {
            val image = data?.extras?.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.image)
            imageView.setImageBitmap(image)
        }
    }

    private fun takePhoto()
    {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }
}