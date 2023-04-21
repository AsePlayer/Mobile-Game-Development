package com.ryan.jokesapp.data.inmemory.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ryan.jokesapp.data.JokeModel

class JokesDaoSQLite (
    context: Context,
    factory: SQLiteDatabase.CursorFactory?,
    name: String = "jokes_db"
) :
    SQLiteOpenHelper(context, name, factory, 1)
{
    val JOKES_TABLE: String = "jokes_table"
    val ID_COL: String = "id"
    val JOKE_QUESTION_COLUMN: String = "joke_question"
    val JOKE_ANSWER_COLUMN: String = "joke_answer"

    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + JOKES_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                JOKE_QUESTION_COLUMN + " TEXT," +
                JOKE_ANSWER_COLUMN + " TEXT)"
                )

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
        Log.d("jokesDBHelper", "Created new db")

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + JOKES_TABLE)
        onCreate(db)
    }

    fun addOne(joke: JokeModel) : JokeModel? {
        val dataPairs = ContentValues()

        dataPairs.put(JOKE_QUESTION_COLUMN, joke.question)
        dataPairs.put(JOKE_ANSWER_COLUMN, joke.answer)

        // choose a "writable" parameter when opening the database since we want to insert a record
        val db = this.writableDatabase

        // check the version. If the table in the SQL statement does not exist,
        // SQLite will automatically create it
        db.version

        // insert command will fail if the datapairs has an error
        val result = db.insert(JOKES_TABLE, null,dataPairs)

        Log.d("jokesDAO", "Added new item to db")
        Log.d("jokesDAO", result.toString())

        db.close()

        return if(result > 0) {
            joke
        } else {
            null
        }
    }

    fun getAll() : List<JokeModel> {
        // open db as "read", no add or delete operations in this function
        val db = this.readableDatabase

        // a cursor is a result set. loop through its members to get each record
        val cursor = db.rawQuery("SELECT * FROM " + JOKES_TABLE, null)

        // results is an empty arrayList to be filled inside the following "while" loop
        var results = arrayListOf<JokeModel>()

        // keep looping until the cursor position is after the last element
        while(!cursor.isAfterLast) {
            // log results for troubleshooting purposes
            Log.d("jokesdbhelper", "getAll: Cursor position ${cursor.position}")

            // hopefully the table has an int, two strings. If not, the exception log message will say otherwise
            try {
                var joke = JokeModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    answerIsVisible = false
                    )
                // visibility is not part oft he database. Assume answer is not visible

                // accumulate all jokes in the results array
                results.add(joke)
            }
            catch (e: Exception) {
                Log.d("jokesDBHelper", "Exception error: $e.message")
            }
            // cursor position +1
            cursor.moveToNext()
        } // end while loop
        // all operations finished
        db.close()
        // send the list jokes back
        return results
    }

    fun getWithSearch(searchTerm: String) : List<JokeModel> {
        // no add/remove operations in this function
        val db = this.readableDatabase

        // create a string with wildcard characters appended in front and back
        // allows for partial matches. for example "pha" inside of "elephant"
        val searchWithWild = "%"+searchTerm+"%"

        val cursor = db.rawQuery(
            "SELECT * FROM " + JOKES_TABLE + " WHERE " +
                    JOKE_QUESTION_COLUMN + " LIKE ? OR " +
                    JOKE_ANSWER_COLUMN + " LIKE ?",
            arrayOf(searchWithWild, searchWithWild)
        )

        // each ? corresponds to an item in the array
        // this statement has two ?. So the array has two elements

        // start with empty list
        var results = arrayListOf<JokeModel>()

        // loop through the cursor. Get a new joke from each line fetched
        while(!cursor.isAfterLast) {
            Log.d("jokesDAO", "Cursor position ${cursor.position}")
            try {
                var joke = JokeModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    answerIsVisible = false
                )
                results.add(joke)
            }
            catch (e: Exception) {
                Log.d("JokeDAO", "Exception error: ${e.message}")
            }
            cursor.moveToNext()
        }
        return results
    }

    fun deleteById(id: Int) : Boolean {
        val db = this.writableDatabase
        val cursor = db.rawQuery("DELETE FROM " + JOKES_TABLE + " WHERE " + ID_COL + " = '" + id + "'", null)

        // if moveToFirst returns a true, then there is at least one row deleted
        val success = cursor.moveToFirst()
        db.close()
        return success
    }
}