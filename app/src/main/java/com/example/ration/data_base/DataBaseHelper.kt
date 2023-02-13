package com.example.ration.data_base

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


internal class DatabaseHelper(private val myContext: Context) :
    SQLiteOpenHelper(myContext, DB_NAME, null, SCHEMA) {
    init {
        DB_PATH = myContext.filesDir.path + DB_NAME
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    fun create_db() {
        val file = File(DB_PATH)
        if (!file.exists()) {
            //получаем локальную бд как поток
            try {
                myContext.assets.open(DB_NAME).use { myInput ->
                    FileOutputStream(DB_PATH).use { myOutput ->

                        // побайтово копируем данные
                        val buffer = ByteArray(1024)
                        var length: Int
                        while (myInput.read(buffer).also { length = it } > 0) {
                            myOutput.write(buffer, 0, length)
                        }
                        myOutput.flush()
                    }
                }
            } catch (ex: IOException) {
                Log.d("DatabaseHelper", ex.message!!)
            }
        }
    }

    @Throws(SQLException::class)
    fun open(): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE)
    }

    companion object {
        private var DB_PATH // полный путь к базе данных
                : String = ""
        private const val DB_NAME = "products.db"
        private const val SCHEMA = 1 // версия базы данных
        const val TABLE = "Kalorii" // название таблицы в бд
        const val TABLE_BREAKFAST = "Breakfast" // название таблицы в бд
        const val TABLE_DRINKS = "Drinks" // название таблицы в бд
        const val TABLE_HOTTER = "Hotter" // название таблицы в бд
        const val TABLE_SALADS = "Salads" // название таблицы в бд
        const val TABLE_SECOND = "Second" // название таблицы в бд

        // названия столбцов
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_CALORIES = "kKal"
        const val COLUMN_PROTEIN = "protein"
        const val COLUMN_FAT = "fats"
        const val COLUMN_CARBOHYDRATE = "carbohydrates"
    }
}