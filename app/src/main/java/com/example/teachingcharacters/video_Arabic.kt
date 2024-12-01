package com.example.teachingcharacters

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class video_Arabic : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_arabic)

        val out1 = findViewById<ImageView>(R.id.back_A)
        out1.setOnClickListener {
           finish()
        }

        val videoAs = findViewById<VideoView>(R.id.videoArabic)

        val mediaController = android.widget.MediaController(this)
        mediaController.setAnchorView(videoAs)
        videoAs.setMediaController(mediaController)

        // احصل على مسار الملف الذي تم وضعه في مجلد raw
        val videoPath = "android.resource://" + packageName + "/" + R.raw.arabic

        // قم بتحديد مصدر الفيديو باستخدام URI
        val videoUri = Uri.parse(videoPath)

        // قم بتعيين مصدر الفيديو لـ VideoView
        videoAs.setVideoURI(videoUri)

        // ابدأ تشغيل الفيديو
        videoAs.start()
    }
}