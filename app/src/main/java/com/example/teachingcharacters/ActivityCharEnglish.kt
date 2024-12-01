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

class ActivityCharEnglish  : AppCompatActivity() {

    lateinit var myDatabase: MyDatabase
    lateinit var cursor: Cursor
    var mp: MediaPlayer? = null

    // تعريف مصفوفة بأسماء الأعمدة في قاعدة البيانات
    val columnNames = arrayOf("Echar", "Eaudio", "Ename", "Eimage")

    // تعريف المتغيرات للتحكم في دوران الصور
    private val handler = Handler()
    private val rotationInterval = 2500L // 2.5 seconds
    private var isRotationActive = false

    // تعريف العناصر المرئية في واجهة المستخدم
    lateinit var imgAudioE: ImageView
    lateinit var imgBehindE: ImageView
    lateinit var imgFrontE: ImageView
    lateinit var imagemusic: ImageView

    // دالة إنشاء النشاط
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("WrongViewCast", "MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_english)

        // تعريف الصورة التي يمكن النقر عليها للعودة للشاشة الرئيسية
        val out1 = findViewById<ImageView>(R.id.outE)
        out1.setOnClickListener {
            performCustomBackAction()
            onBackPressed()
        }

        myDatabase = MyDatabase(this)
        myDatabase.openDB()
        val id = intent.getIntExtra("id", 0)

        val imgCar = findViewById<ImageView>(R.id.imgCharE)
        val name = findViewById<TextView>(R.id.textNameE)
        imgAudioE = findViewById(R.id.imgAudioE)
        imgBehindE = findViewById(R.id.imgBehindE)
        imgFrontE = findViewById(R.id.imgFrontE)
        imagemusic = findViewById(R.id.imagemusic)
        val imgA = findViewById<ImageView>(R.id.imageE)

        // استعراض البيانات من قاعدة البيانات
        cursor = myDatabase.selectEnglish()

        if (cursor.moveToFirst()) {
            updateUI(cursor)

            // تحكم في الأزرار للانتقال بين الحروف
            val btnForward = findViewById<ImageView>(R.id.imgBehindE)
            val btnBackward = findViewById<ImageView>(R.id.imgFrontE)

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
            imgAudioE.setOnClickListener {
                mp?.release()
                val audioPath = cursor.getInt(cursor.getColumnIndexOrThrow("Eaudio"))
                mp = MediaPlayer.create(this, audioPath)
                mp?.start()
            }

            // التحكم في دوران الصور
            imagemusic.setOnClickListener {
                if (!isRotationActive) {
                    startRotationTask()
                    // إخفاء المفاتيح عند بدء تشغيل الموسيقى
                    imgAudioE.visibility = View.GONE
                    imgBehindE.visibility = View.GONE
                    imgFrontE.visibility = View.GONE
                } else {
                    stopRotationTask()
                    // إظهار المفاتيح عند توقف الموسيقى
                    imgAudioE.visibility = View.VISIBLE
                    imgBehindE.visibility = View.VISIBLE
                    imgFrontE.visibility = View.VISIBLE
                }
            }
        }
    }

    // تحديث واجهة المستخدم بناءً على مؤشر السجل الحالي
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun updateUI(cursor: Cursor) {
        val imgCar = findViewById<ImageView>(R.id.imgCharE)
        val name = findViewById<TextView>(R.id.textNameE)
        val imgAudio = findViewById<ImageView>(R.id.imgAudioE)
        val imgA = findViewById<ImageView>(R.id.imageE)

        // تحديث الصورة والنص والصوت
        val imageCharResourceId = cursor.getInt(cursor.getColumnIndexOrThrow("Echar"))
        imgCar.setImageResource(imageCharResourceId)

        name.text = cursor.getString(cursor.getColumnIndexOrThrow("Ename"))
        mp?.release()

        val audioPath = cursor.getInt(cursor.getColumnIndexOrThrow("Eaudio"))
        mp = MediaPlayer.create(this, audioPath)
        mp?.start()

        val imageResourceId = cursor.getInt(cursor.getColumnIndexOrThrow("Eimage"))
        imgA.setImageResource(imageResourceId)
    }

    // بدء دوران الصور
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun startRotationTask() {
        isRotationActive = true
        rotateToNextItem()
        handler.postDelayed(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
            override fun run() {
                rotateToNextItem()
                if (cursor.isLast) {
                    stopRotationTask()
                } else {
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
            imgAudioE.visibility = View.GONE
            imgBehindE.visibility = View.GONE
            imgFrontE.visibility = View.GONE
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

    // تنفيذ إجراء العودة المخصصة
    private fun performCustomBackAction() {

    }
}
