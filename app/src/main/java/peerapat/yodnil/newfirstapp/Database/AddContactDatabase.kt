package peerapat.yodnil.newfirstapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AddContact::class], version = 1, exportSchema = false)
abstract class AddContactDatabase : RoomDatabase(){
    abstract val addContactDatabaseDao: AddContactDatabaseDao
    companion object{
        @Volatile
        private var INSTANCE: peerapat.yodnil.newfirstapp.Database.AddContactDatabase? = null
        fun getInstance(context: Context): peerapat.yodnil.newfirstapp.Database.AddContactDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        peerapat.yodnil.newfirstapp.Database.AddContactDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}