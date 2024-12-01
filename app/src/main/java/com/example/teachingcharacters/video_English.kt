package com.example.teachingcharacters

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView

class video_English : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_english)

        val out1 = findViewById<ImageView>(R.id.back_E)
        out1.setOnClickListener {
            finish()
        }

        val videoE = findViewById<VideoView>(R.id.videoEnglish)

        val mediaController = android.widget.MediaController(this)
        mediaController.setAnchorView(videoE)
        videoE.setMediaController(mediaController)

        // احصل على مسار الملف الذي تم وضعه في مجلد raw
        val videoPath = "android.resource://" + packageName + "/" + R.raw.english

        // قم بتحديد مصدر الفيديو باستخدام URI
        val videoUri = Uri.parse(videoPath)

        // قم بتعيين مصدر الفيديو لـ VideoView
        videoE.setVideoURI(videoUri)

        // ابدأ تشغيل الفيديو
        videoE.start()
    }
}