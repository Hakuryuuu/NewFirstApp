package peerapat.yodnil.newfirstapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddContactDatabaseDao {
    @Insert
    fun insert(contact: AddContact)
    @Query("SELECT * from contact_table")
    fun get(): LiveData<List<AddContact>>
}
