package com.shadsluiter.jokesapp.data.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ryan.jokesapp.data.room.JokeDaoRoom
import com.ryan.jokesapp.data.room.JokeEntity

@Database(entities = [JokeEntity::class],
          version = 1,
          exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDaoRoom

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "joke_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
