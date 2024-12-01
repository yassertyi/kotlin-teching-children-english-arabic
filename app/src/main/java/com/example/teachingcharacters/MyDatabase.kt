package com.example.teachingcharacters

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.Image
import android.media.session.MediaSession.Token
import android.widget.Toast
import androidx.core.content.contentValuesOf

class MyDatabase(context: Context) {

     val ctx = context
    //1- database   table   key   create

    companion object{

        val dbName = "Education"
        val dbversion = 1

        //Arabic
        val tableArabic = "tarabic"
        val K_A_ID = "_id"
        val K_A_Char = "Achar"
        val K_A_Name = "Aname"
        val K_A_Audio = "Aaudio"
        val K_A_Image = "Aimage"

        val  CreateTableArabic = "create table $tableArabic("+
        "$K_A_ID integer primary key autoincrement,"+
        "$K_A_Char integer ,"+
        "$K_A_Name text,"+
         "$K_A_Audio integer,"+
        "$K_A_Image integer);"

        //English

        val tableEnglish = "tenglish"
        val K_E_ID = "_id"
        val K_E_Char = "Echar"
        val K_E_Name = "Ename"
        val K_E_Audio = "Eaudio"
        val K_E_Image = "Eimage"

        val  CreateTableEnglish = "create table $tableEnglish("+
                "$K_E_ID integer primary key autoincrement,"+
                "$K_E_Char integer ,"+
                "$K_E_Name text,"+
                "$K_E_Audio integer,"+
                "$K_E_Image integer);"

    }

    //2- create  upgrade  (sqlite open helper)

    class MyOpenHelper(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(p0: SQLiteDatabase?) {
           p0!!.execSQL(CreateTableArabic)
           p0.execSQL(CreateTableEnglish)


            insertArabic(p0, R.drawable.a1, "أرنب", R.raw.aa1, R.drawable.aimg1)
            insertArabic(p0, R.drawable.a2, "باب", R.raw.aa2, R.drawable.aimg2)
            insertArabic(p0, R.drawable.a3, "تلفاز", R.raw.aa3, R.drawable.aimg3)
            insertArabic(p0, R.drawable.a4, "ثلاجة", R.raw.aa4, R.drawable.aimg4)
            insertArabic(p0, R.drawable.a5, "جمل", R.raw.aa5, R.drawable.aimg5)
            insertArabic(p0, R.drawable.a6, "حليب", R.raw.aa6, R.drawable.aimg6)
            insertArabic(p0, R.drawable.a7, "خيمة", R.raw.aa7, R.drawable.aimg7)
            insertArabic(p0, R.drawable.a8, "دولاب", R.raw.aa8, R.drawable.aimg8)
            insertArabic(p0, R.drawable.a9, "ذرة", R.raw.aa9, R.drawable.aimg9)
            insertArabic(p0, R.drawable.a10, "رياضة", R.raw.aa10, R.drawable.aimg10)
            insertArabic(p0, R.drawable.a11, "زرافة", R.raw.aa11, R.drawable.aimg11)
            insertArabic(p0, R.drawable.a12, "سيارة", R.raw.aa12, R.drawable.aimg12)
            insertArabic(p0, R.drawable.a13, "شجرة", R.raw.aa13, R.drawable.aimg13)
            insertArabic(p0, R.drawable.a14, "صندوق", R.raw.aa14, R.drawable.aimg14)
            insertArabic(p0, R.drawable.a15, "ضابط", R.raw.aa15, R.drawable.aimg15)
            insertArabic(p0, R.drawable.a16, "طائرة", R.raw.aa16, R.drawable.aimg16)
            insertArabic(p0, R.drawable.a17, "ظل", R.raw.aa17, R.drawable.aimg17)
            insertArabic(p0, R.drawable.a18, "عصير", R.raw.aa18, R.drawable.aimg18)
            insertArabic(p0, R.drawable.a19, "غيمة", R.raw.aa19, R.drawable.aimg19)
            insertArabic(p0, R.drawable.a20, "فستان", R.raw.aa20, R.drawable.aimg20)
            insertArabic(p0, R.drawable.a21, "قطار", R.raw.aa21, R.drawable.aimg21)
            insertArabic(p0, R.drawable.a22, "كرسي", R.raw.aa22, R.drawable.aimg22)
            insertArabic(p0, R.drawable.a23, "لسان", R.raw.aa23, R.drawable.aimg23)
            insertArabic(p0, R.drawable.a24, "مفتاح", R.raw.aa24, R.drawable.aimg24)
            insertArabic(p0, R.drawable.a25, "نجمة", R.raw.aa25, R.drawable.aimg25)
            insertArabic(p0, R.drawable.a26, "هاتف", R.raw.aa26, R.drawable.aimg26)
            insertArabic(p0, R.drawable.a27, "وردة", R.raw.aa27, R.drawable.aimg27)
            insertArabic(p0, R.drawable.a28, "يد",R.raw.aa28, R.drawable.aimg28)



            insertEnglish(p0, R.drawable.ea1, "Apple", R.raw.ea1, R.drawable.eimg1)
            insertEnglish(p0, R.drawable.eb2, "Banana", R.raw.ea2, R.drawable.eimg2)
            insertEnglish(p0, R.drawable.ec3, "Cat", R.raw.ea3, R.drawable.eimg3)
            insertEnglish(p0, R.drawable.ed4, "Dog",R.raw.ea4, R.drawable.eimg4)
            insertEnglish(p0, R.drawable.ee5, "Egg", R.raw.ea5, R.drawable.eimg5)
            insertEnglish(p0, R.drawable.ef6, "Fish", R.raw.ea6, R.drawable.eimg6)
            insertEnglish(p0, R.drawable.eg7, "Glasses", R.raw.ea7, R.drawable.eimg7)
            insertEnglish(p0, R.drawable.eh8, "House", R.raw.ea8, R.drawable.eimg8)
            insertEnglish(p0, R.drawable.ei9, "Ice cream", R.raw.ea9, R.drawable.eimg9)
            insertEnglish(p0, R.drawable.ej10, "Jam", R.raw.ea10, R.drawable.eimg10)
            insertEnglish(p0, R.drawable.ek11, "Key", R.raw.ea11, R.drawable.eimg11)
            insertEnglish(p0, R.drawable.el12, "Loin", R.raw.ea12, R.drawable.eimg12)
            insertEnglish(p0, R.drawable.em13, "Monkey", R.raw.ea13, R.drawable.eimg13)
            insertEnglish(p0, R.drawable.en14, "Nail", R.raw.ea14, R.drawable.eimg14)
            insertEnglish(p0, R.drawable.eo15, "Orange", R.raw.ea15, R.drawable.eimg15)
            insertEnglish(p0, R.drawable.ep16, "Pencil", R.raw.ea16, R.drawable.eimg16)
            insertEnglish(p0, R.drawable.eq17, "Queen", R.raw.ea17, R.drawable.eimg17)
            insertEnglish(p0, R.drawable.er18, "Robot", R.raw.ea18, R.drawable.eimg18)
            insertEnglish(p0, R.drawable.es19, "Strawberry", R.raw.ea19, R.drawable.eimg19)
            insertEnglish(p0, R.drawable.et20, "Tree", R.raw.ea20, R.drawable.eimg20)
            insertEnglish(p0, R.drawable.eu21, "Umbrella", R.raw.ea21, R.drawable.eimg21)
            insertEnglish(p0, R.drawable.ev22, "Vegetable", R.raw.ea22, R.drawable.eimg22)
            insertEnglish(p0, R.drawable.ew23, "Window", R.raw.ea23, R.drawable.eimg23)
            insertEnglish(p0, R.drawable.ex24, "X-ray", R.raw.ea24, R.drawable.eimg24)
            insertEnglish(p0, R.drawable.ey25, "Yogurt", R.raw.ea25, R.drawable.eimg25)
            insertEnglish(p0, R.drawable.ez26, "Zebra", R.raw.ea26, R.drawable.eimg26)
        }

        override fun onUpgrade(p0: SQLiteDatabase?,p1: Int, p2: Int) {

        }
        fun insertArabic(db: SQLiteDatabase,char:  Int, name: String, aAudio: Int, image: Int){

            val cv = ContentValues()
            cv.put(K_A_Char, char)
            cv.put(K_A_Name, name)
            cv.put(K_A_Audio, aAudio)
            cv.put(K_A_Image, image)

           db.insert(tableArabic,null,cv)
        }

        fun insertEnglish(db: SQLiteDatabase,char:  Int, name: String, eAudio: Int, image: Int){

            val cv = ContentValues()
            cv.put(K_E_Char, char)
            cv.put(K_E_Name, name)
            cv.put(K_E_Audio, eAudio)
            cv.put(K_E_Image, image)

            db.insert(tableEnglish,null,cv)

        }

    }

    //3- open   close
    lateinit var db:SQLiteDatabase

    fun  openDB(){
        val  moh = MyOpenHelper(ctx, dbName,null, dbversion)
        db = moh.writableDatabase
    }

    fun closeDB(){
        db.close()
    }

    //4- select   insert  update  delete

    //Arabic
    fun insertArabic(char:  Int, name: String, aAudio: Int, image: Int){

        val cv = ContentValues()
        cv.put(K_A_Char, char)
        cv.put(K_A_Name, name)
        cv.put(K_A_Audio, aAudio)
        cv.put(K_A_Image, image)

        val l = db.insert(tableArabic,null,cv)
        if (l>=1) Toast.makeText(ctx,"Done", Toast.LENGTH_SHORT).show()
    }


    fun selectArabic():Cursor{
        val query = "SELECT * FROM $tableArabic ORDER BY $K_A_ID ASC"
        return db.rawQuery(query, null)

    }


    //English
    fun insertEnglish(char:  Int, name: String, eAudio: Int, image: Int){

        val cv = ContentValues()
        cv.put(K_E_Char, char)
        cv.put(K_E_Name, name)
        cv.put(K_E_Audio, eAudio)
        cv.put(K_E_Image, image)

        val l = db.insert(tableEnglish,null,cv)
        if (l>=1) Toast.makeText(ctx,"Done", Toast.LENGTH_SHORT).show()
    }

    fun selectEnglish():Cursor{
        val query = "SELECT * FROM $tableEnglish ORDER BY $K_E_ID ASC"
        return db.rawQuery(query, null)
    }



}

