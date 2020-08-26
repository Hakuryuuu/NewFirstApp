package peerapat.yodnil.newfirstapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import peerapat.yodnil.newfirstapp.Database.AddContactDatabaseDao
import peerapat.yodnil.newfirstapp.databinding.FragmentContactBinding

class AddContactViewModel(
    val database: AddContactDatabaseDao,
    private val binding: FragmentContactBinding,
    application: Application
) : AndroidViewModel(application) {
}