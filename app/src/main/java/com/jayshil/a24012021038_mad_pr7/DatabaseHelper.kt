package com.jayshil.a24012021038_mad_pr7

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "persons_db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(PersonDbTableData.CREATE_TABLE)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS ${PersonDbTableData.TABLE_NAME}")
        onCreate(db)
    }

    fun insertPerson(person: Person): Long {

        val db = writableDatabase

        val values = ContentValues()

        values.put(PersonDbTableData.COLUMN_ID, person.id)
        values.put(PersonDbTableData.COLUMN_PERSON_NAME, person.name)
        values.put(PersonDbTableData.COLUMN_PERSON_EMAIL_ID, person.emailId)
        values.put(PersonDbTableData.COLUMN_PERSON_PHONE_NO, person.phoneNo)
        values.put(PersonDbTableData.COLUMN_PERSON_ADDRESS, person.address)
        values.put(PersonDbTableData.COLUMN_PERSON_GPS_LAT, person.latitude)
        values.put(PersonDbTableData.COLUMN_PERSON_GPS_LONG, person.longitude)

        val result = db.insert(
            PersonDbTableData.TABLE_NAME,
            null,
            values
        )

        db.close()

        return result
    }

    fun getAllPersons(): ArrayList<Person> {

        val personList = ArrayList<Person>()

        val db = readableDatabase

        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM ${PersonDbTableData.TABLE_NAME}",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val person = Person(

                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getDouble(6)

                )

                personList.add(person)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return personList
    }

    fun deletePerson(id: String) {

        val db = writableDatabase

        db.delete(
            PersonDbTableData.TABLE_NAME,
            "${PersonDbTableData.COLUMN_ID}=?",
            arrayOf(id)
        )

        db.close()
    }

    fun updatePerson(person: Person): Int {

        val db = writableDatabase

        val values = ContentValues()

        values.put(PersonDbTableData.COLUMN_PERSON_NAME, person.name)
        values.put(PersonDbTableData.COLUMN_PERSON_EMAIL_ID, person.emailId)
        values.put(PersonDbTableData.COLUMN_PERSON_PHONE_NO, person.phoneNo)
        values.put(PersonDbTableData.COLUMN_PERSON_ADDRESS, person.address)
        values.put(PersonDbTableData.COLUMN_PERSON_GPS_LAT, person.latitude)
        values.put(PersonDbTableData.COLUMN_PERSON_GPS_LONG, person.longitude)

        return db.update(
            PersonDbTableData.TABLE_NAME,
            values,
            "${PersonDbTableData.COLUMN_ID}=?",
            arrayOf(person.id)
        )
    }
}