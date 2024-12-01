package com.example.teachingcharacters

import android.annotation.SuppressLint
import android.database.Cursor
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class ActivityCharArabic : AppCompatActivity() {

    // تعريف المتغيرات اللازمة
    lateinit var myDatabase: MyDatabase
    lateinit var cursor: Cursor
    var mp: MediaPlayer? = null

    // تعريف مصفوفة بأسماء الأعمدة في قاعدة البيانات
    val columnNames = arrayOf("Achar", "Aname", "Aaudio", "Aimage")

    // تعريف المتغيرات للتحكم في دوران الصور
    private val handler = Handler()
    private val rotationInterval = 3500L // 4 seconds
    private var isRotationActive = false

    // تعريف العناصر المرئية في واجهة المستخدم
    lateinit var imgAudioArabic: ImageView
    lateinit var behindArabic: ImageView
    lateinit var frontArabic: ImageView
    lateinit var music: ImageView

    // دالة إنشاء النشاط
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("WrongViewCast", "MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_arabic)

        // تعريف الصورة التي يمكن النقر عليها للعودة للشاشة الرئيسية
        val out1 = findViewById<ImageView>(R.id.imageViewOut)
        out1.setOnClickListener {
            finish()
        }

        // فتح قاعدة البيانات واسترجاع معلومات الحرف المحدد
        myDatabase = MyDatabase(this)
        myDatabase.openDB()
        val id = intent.getIntExtra("id", 0)

        // تعيين العناصر المرئية والتحكم فيها
        val imgCar = findViewById<ImageView>(R.id.imgCharArabic)
        val name = findViewById<TextView>(R.id.textNameArabic)
        imgAudioArabic = findViewById(R.id.imgAudioArabic)
        behindArabic = findViewById(R.id.behindArabic)
        frontArabic = findViewById(R.id.frontArabic)
        music = findViewById(R.id.music)
        val imgA = findViewById<ImageView>(R.id.imageArabic)

        // استعراض البيانات من قاعدة البيانات
        cursor = myDatabase.selectArabic()

        if (cursor.moveToFirst()) {
            updateUI(cursor)

            // تحكم في الأزرار للانتقال بين الحروف
            val btnForward = findViewById<ImageView>(R.id.behindArabic)
            val btnBackward = findViewById<ImageView>(R.id.frontArabic)

            btnBackward.visibility = View.GONE

            // إعداد الزر الأمامي للتنقل للأمام
            btnForward.setOnClickListener {
                if (cursor.moveToNext()) {
                    updateUI(cursor)
                    if (cursor.isLast) {
                        btnForward.visibility = View.GONE
                    }
                    if (btnBackward.visibility == View.GONE) {
                        btnBackward.visibility = View.VISIBLE
                    }
                }
            }

            // إعداد الزر الخلفي للتنقل للخلف
            btnBackward.setOnClickListener {
                if (cursor.moveToPrevious()) {
                    updateUI(cursor)
                    if (cursor.isFirst) {
                        btnBackward.visibility = View.GONE
                    }
                    if (btnForward.visibility == View.GONE) {
                        btnForward.visibility = View.VISIBLE
                    }
                }
            }

            // تشغيل الصوت عند النقر على الرمز الصوتي
            imgAudioArabic.setOnClickListener {
                mp?.release()
                val audioPath = cursor.getInt(cursor.getColumnIndexOrThrow("Aaudio"))
                mp = MediaPlayer.create(this, audioPath)
                mp?.start()
            }

            // التحكم في دوران الصور
            music.setOnClickListener {
                if (!isRotationActive) {
                    startRotationTask()
                    // إخفاء المفاتيح عند بدء تشغيل الموسيقى
                    imgAudioArabic.visibility = View.GONE
                    behindArabic.visibility = View.GONE
                    frontArabic.visibility = View.GONE
                } else {
                    stopRotationTask()
                    // إظهار المفاتيح عند توقف الموسيقى
                    imgAudioArabic.visibility = View.VISIBLE
                    behindArabic.visibility = View.VISIBLE
                    frontArabic.visibility = View.VISIBLE
                }
            }
        }
    }

    // تحديث واجهة المستخدم بناءً على مؤشر السجل الحالي
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun updateUI(cursor: Cursor) {
        val imgCar = findViewById<ImageView>(R.id.imgCharArabic)
        val name = findViewById<TextView>(R.id.textNameArabic)
        val imgAudio = findViewById<ImageView>(R.id.imgAudioArabic)
        val imgA = findViewById<ImageView>(R.id.imageArabic)

        // تحديث الصورة والنص والصوت
        val imageCharResourceId = cursor.getInt(cursor.getColumnIndexOrThrow("Achar"))
        imgCar.setImageResource(imageCharResourceId)

        name.text = cursor.getString(cursor.getColumnIndexOrThrow("Aname"))

        mp?.release()

        val audioPath = cursor.getInt(cursor.getColumnIndexOrThrow("Aaudio"))
        mp = MediaPlayer.create(this, audioPath)
        mp?.start()

        val imageResourceId = cursor.getInt(cursor.getColumnIndexOrThrow("Aimage"))
        imgA.setImageResource(imageResourceId)
    }

    // بدء دوران الصور
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun startRotationTask() {
        //يدل على ان الدوران شغال
        isRotationActive = true
        rotateToNextItem()
        handler.postDelayed(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
            override fun run() {
                rotateToNextItem()
                if (cursor.isLast) {
                    stopRotationTask()
                } else {
                    //تأجيل تنفيذ تحديث الصورة مع فاصل زمني
                    handler.postDelayed(this, rotationInterval)
                }
            }
        }, rotationInterval)
    }

    // التحول إلى الصورة التالية في دوران الصور
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun rotateToNextItem() {
        if (cursor.moveToNext()) {
            updateUI(cursor)
        } else {
            cursor.moveToFirst()
            updateUI(cursor)
            // إخفاء المفاتيح عند التحول التلقائي للسجلات
            imgAudioArabic.visibility = View.GONE
            behindArabic.visibility = View.GONE
            frontArabic.visibility = View.GONE
        }
    }

    // إيقاف دوران الصور
    private fun stopRotationTask() {
        isRotationActive = false
        handler.removeCallbacksAndMessages(null)
    }

    // إغلاق قاعدة البيانات والمؤشر والمشغل عند تدمير النشاط
    override fun onDestroy() {
        super.onDestroy()
        myDatabase.closeDB()
        cursor.close()
        mp?.release()
        handler.removeCallbacksAndMessages(null)
    }

}
