package com.example.reto2.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.reto2.db.DBHelper
import com.example.reto2.model.Element

class ElementDAO(private var context: Context) {

    fun insertElement(element:Element) {
        val db:SQLiteDatabase = DBHelper(context, "elements_db", null, 1).writableDatabase
        val content:ContentValues = ContentValues()
        content.put("name", element.name)
        db.insert("element", null, content)
        db.close()
    }

    fun getAllElements():List<Element> {
        val db:SQLiteDatabase = DBHelper(context, "elements_db", null, 1).readableDatabase
        val res: Cursor = db.query("element", arrayOf("name"), null, null, null, null, null)
        var count:Int = 0;
        var elements = arrayListOf<Element>()

        while (res.moveToNext()) {
            count++
            val name = res.getString(res.getColumnIndexOrThrow("name"))
            elements.add(Element(count, name))
        }

        res.close()
        db.close()
        return elements
    }

    fun clearList() {
        val db:SQLiteDatabase = DBHelper(context, "elements_db", null, 1).writableDatabase
        db.delete("element", null, null)
        db.close()
    }

}