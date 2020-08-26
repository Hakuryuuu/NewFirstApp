package peerapat.yodnil.newfirstapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import peerapat.yodnil.newfirstapp.Database.AddContactDatabaseDao
import peerapat.yodnil.newfirstapp.databinding.FragmentContactBinding

class AddContactViewmodelFactory (
    private val dataSource: AddContactDatabaseDao,
    private val binding: FragmentContactBinding,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            return AddContactViewModel(dataSource, binding, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}