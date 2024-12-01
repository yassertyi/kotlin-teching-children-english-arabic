package com.example.teachingcharacters

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class coala : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coala)

        val ima = findViewById<ImageView>(R.id.image_arabic)
        val ime = findViewById<ImageView>(R.id.image_english)
        val out = findViewById<ImageView>(R.id.imageView10)
        val shar = findViewById<ImageView>(R.id.imageView11)
        val videoA = findViewById<ImageView>(R.id.video_Arabic)
        val videoE = findViewById<ImageView>(R.id.video_English)
        val det = findViewById<ImageView>(R.id.details)

        ima.setOnClickListener {
            val intent = Intent( this,ActivityCharArabic::class.java)
            startActivity(intent)
        }
        ime.setOnClickListener {
            val intent = Intent( this,ActivityCharEnglish::class.java)
            startActivity(intent)
        }

        videoA.setOnClickListener {
            val intent = Intent(this,video_Arabic::class.java)
            startActivity(intent)
        }

        videoE.setOnClickListener {
            val intent = Intent(this,video_English::class.java)
            startActivity(intent)
        }

        det.setOnClickListener {
            val intent = Intent( this,Details::class.java)
            startActivity(intent)
        }


        shar.setOnClickListener {
            val appPackageName = packageName
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, " Google Play: https://play.google.com/store/apps/details?id=$appPackageName")
            }
            startActivity(Intent.createChooser(shareIntent, "مشاركة التطبيق عبر:"))
        }

        out.setOnClickListener {
            showExitConfirmationDialog() {

            }

        }
    }

    private fun showExitConfirmationDialog(function: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("تأكيد الخروج")
        builder.setMessage("هل أنت متأكد من رغبتك في الخروج؟")

        builder.setPositiveButton("نعم") { _, _ ->
            finish()
        }

        builder.setNegativeButton("لا") { _, _ ->

        }
        builder.show()
    }
}
